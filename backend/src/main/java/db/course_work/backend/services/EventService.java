package db.course_work.backend.services;

import db.course_work.backend.dto.request.EventRequest;
import db.course_work.backend.dto.request.MeetingRequest;
import db.course_work.backend.entities.Event;
import db.course_work.backend.entities.Meeting;
import db.course_work.backend.entities.Member;
import db.course_work.backend.entities.Premise;
import db.course_work.backend.exceptions.*;
import db.course_work.backend.repositories.EventRepository;
import db.course_work.backend.repositories.MeetingRepository;
import db.course_work.backend.repositories.MemberRepository;
import db.course_work.backend.specifications.EventSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {
    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;
    private final MeetingRepository meetingRepository;
    private final SynagogueService synagogueService;

    private boolean validateScheduledTime(OffsetDateTime offsetDateTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime scheduledDateTime = toSystemDate(offsetDateTime);

        return scheduledDateTime.isAfter(now);
    }

    private Member getMember(long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) throw new UserNotFoundException();
        return memberOptional.get();
    }

    private LocalDateTime toSystemDate(OffsetDateTime dateTime) {
        return dateTime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    private Premise getPremise(Member member, long premiseId) {
        Optional<Premise> premiseOptional = member.getSynagogue().getPremises().stream().filter(premise1 -> premise1.getId() == premiseId).findAny();
        if (premiseOptional.isEmpty()) throw new PremiseNotFoundException();
        return premiseOptional.get();
    }

    public EventService(MemberRepository memberRepository, EventRepository eventRepository, MeetingRepository meetingRepository, SynagogueService synagogueService) {
        this.memberRepository = memberRepository;
        this.eventRepository = eventRepository;
        this.meetingRepository = meetingRepository;
        this.synagogueService = synagogueService;
    }

    public List<Event> getEventsInSynagogue(long synagogueId, Optional<Long> memberId, boolean onlyInFuture) {
        Specification<Event> spec = Specification.where(EventSpecs.hasSynagogue(synagogueId));
        if (memberId.isPresent()) spec = spec.and(EventSpecs.hasMemberWithId(memberId.get()));
        if (onlyInFuture) spec = spec.and(EventSpecs.dateAfter(OffsetDateTime.now()));
        return eventRepository.findAll(spec);
    }

    public List<Event> getEventsInSynagogue(long memberId) {
        return getEventsInSynagogue(memberId, Optional.empty(), false);
    }

    public List<Meeting> getMeetings(long synagogueId, Optional<Long> memberId, boolean onlyInFuture) {
        Specification<Meeting> spec = Specification.where(EventSpecs.hasSynagogue(synagogueId));
        if (memberId.isPresent()) spec = spec.and(EventSpecs.hasMemberWithId(memberId.get()));
        if (onlyInFuture) spec = spec.and(EventSpecs.dateAfter(OffsetDateTime.now()));
        return meetingRepository.findAll(spec);
    }

    public List<Meeting> getMeetings(long memberId) {
        return getMeetings(memberId, Optional.empty(), false);
    }


    @Transactional
    public Event scheduleEventAtSynagogueOfMember(long memberId, EventRequest eventDto) {
        Member member = getMember(memberId);

        if (!validateScheduledTime(eventDto.getDate())) throw new SchedulingEventException();

        Set<Member> members = memberRepository.findMembersByIdIn(eventDto.getMemberIds());
        Event event = new Event(eventDto.getType(), eventDto.getDescription(), eventDto.getDate(), member.getSynagogue(), members);
        eventRepository.save(event);
        return event;
    }

    @Transactional
    public Meeting scheduleMeetingAtSynagogueOfMember(long memberId, MeetingRequest meetingDto) {
        Member member = getMember(memberId);

        if (!validateScheduledTime(meetingDto.getDate())) throw new SchedulingEventException();
        if (meetingDto.getMemberIds().size() > meetingDto.getMaxVisitors()) throw new MeetingMaxVisitorsException();

        Set<Member> members = memberRepository.findMembersByIdIn(meetingDto.getMemberIds());
        Premise premise = getPremise(member, meetingDto.getPremiseId());

        Meeting meeting = new Meeting(meetingDto.getType(),
                meetingDto.getDescription(),
                meetingDto.getDate(),
                member.getSynagogue(),
                members,
                premise,
                meetingDto.getMaxVisitors(),
                meetingDto.getFood());

        eventRepository.save(meeting);
        return meeting;
    }

    @Transactional
    public boolean reserveSeatAtMeeting(long memberId, long meetingId) {
        Member member = getMember(memberId);

        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingId);
        if (meetingOptional.isEmpty()) throw new MeetingNotFoundException();
        Meeting meeting = meetingOptional.get();

        if (!validateScheduledTime(meeting.getDate()) || !meeting.addMember(member))
            return false;

        meetingRepository.save(meeting);
        return true;
    }
}
