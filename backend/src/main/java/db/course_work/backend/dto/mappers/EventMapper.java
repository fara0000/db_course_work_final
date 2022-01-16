package db.course_work.backend.dto.mappers;

import db.course_work.backend.dto.response.EventDto;
import db.course_work.backend.dto.response.EventList;
import db.course_work.backend.dto.response.MeetingDto;
import db.course_work.backend.dto.response.MeetingList;
import db.course_work.backend.entities.Event;
import db.course_work.backend.entities.Meeting;
import db.course_work.backend.entities.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {
    private final SynagogueMapper synagogueMapper;

    public EventMapper(SynagogueMapper synagogueMapper) {
        this.synagogueMapper = synagogueMapper;
    }

    public EventDto convertEventToDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .type(event.getType())
                .description(event.getDescription())
                .date(event.getDate())
                .memberCount(event.getMembers().size())
                .build();
    }

    public EventList convertEventsToDto(List<Event> events) {
        List<EventDto> eventDtos = events.stream().map(this::convertEventToDto).collect(Collectors.toList());
        return new EventList(eventDtos);
    }

    public MeetingDto convertMeetingToDto(Meeting meeting) {
        return MeetingDto.meetingBuilder()
                .id(meeting.getId())
                .type(meeting.getType())
                .description(meeting.getDescription())
                .date(meeting.getDate())
                .memberCount(meeting.getMembers().size())
                .premise(synagogueMapper.convertPremiseToDto(meeting.getPremise()))
                .maxVisitors(meeting.getMaxVisitors())
                .food(meeting.getFood())
                .build();
    }

    public MeetingList convertMeetingsToDto(List<Meeting> meetings) {
        List<MeetingDto> meetingDtos = meetings.stream().map(this::convertMeetingToDto).collect(Collectors.toList());
        return new MeetingList(meetingDtos);
    }
}
