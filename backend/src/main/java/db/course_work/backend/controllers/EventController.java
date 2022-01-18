package db.course_work.backend.controllers;

import db.course_work.backend.dto.mappers.EventMapper;
import db.course_work.backend.dto.request.EventRequest;
import db.course_work.backend.dto.request.MeetingRequest;
import db.course_work.backend.dto.response.EventDto;
import db.course_work.backend.dto.response.EventList;
import db.course_work.backend.dto.response.MeetingDto;
import db.course_work.backend.dto.response.MeetingList;
import db.course_work.backend.entities.Event;
import db.course_work.backend.entities.Meeting;
import db.course_work.backend.entities.Member;
import db.course_work.backend.services.EventService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    private Optional<Long> getIdIfRequired(boolean required, Member user) {
        return Optional.ofNullable(required ? user.getId() : null);
    }

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("")
    public EventList getAllEvents(@RequestParam(defaultValue = "false") boolean onlyMemberEvents, @AuthenticationPrincipal Member user) {
        List<Event> events = eventService.getEventsInSynagogue(user.getSynagogue().getId(), getIdIfRequired(onlyMemberEvents, user), false);
        return eventMapper.convertEventsToDto(events);
    }

    @GetMapping(value = "", params = "inFuture=true")
    public EventList getFutureEvents(@RequestParam(defaultValue = "false") boolean onlyMemberEvents, @AuthenticationPrincipal Member user) {
        List<Event> events = eventService.getEventsInSynagogue(user.getSynagogue().getId(), getIdIfRequired(onlyMemberEvents, user), true);
        return eventMapper.convertEventsToDto(events);
    }

    @GetMapping(value = "", params = "onlyMeetings=true")
    public MeetingList getAllMeetings(@RequestParam(defaultValue = "false") boolean onlyMemberEvents, @AuthenticationPrincipal Member user) {
        List<Meeting> meetings = eventService.getMeetings(user.getSynagogue().getId(), getIdIfRequired(onlyMemberEvents, user), false);
        return eventMapper.convertMeetingsToDto(meetings);
    }

    @GetMapping(value = "", params = {"onlyMeetings=true", "inFuture=true"})
    public MeetingList getFutureMeetings(@RequestParam(defaultValue = "false") boolean onlyMemberEvents, @AuthenticationPrincipal Member user) {
        List<Meeting> meetings = eventService.getMeetings(user.getSynagogue().getId(), getIdIfRequired(onlyMemberEvents, user), true);
        return eventMapper.convertMeetingsToDto(meetings);
    }

    @PostMapping("")
    public EventDto scheduleEvent(@RequestBody @Valid EventRequest eventRequest, @AuthenticationPrincipal Member user) {
        Event event = eventService.scheduleEventAtSynagogueOfMember(user.getId(), eventRequest);
        return eventMapper.convertEventToDto(event);
    }

    @PostMapping(value = "", params = "meeting=true")
    public MeetingDto scheduleMeeting(@RequestBody @Valid MeetingRequest meetingRequest, @AuthenticationPrincipal Member user) {
        Meeting meeting = eventService.scheduleMeetingAtSynagogueOfMember(user.getId(), meetingRequest);
        return eventMapper.convertMeetingToDto(meeting);
    }

    @PostMapping(value = "/{meetingId}/reserveSeat", params = "meeting=true")
    public boolean reserveSeatAtMeeting(@PathVariable("meetingId") long meetingId, @AuthenticationPrincipal Member user) {
        return eventService.reserveSeatAtMeeting(user.getId(), meetingId);
    }
}
