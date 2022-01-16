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
import db.course_work.backend.services.EventService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("")
    public EventList getAllEvents() {
        List<Event> events = eventService.getAllEvents(1);
        return eventMapper.convertEventsToDto(events);
    }

    @GetMapping(value = "", params = "inFuture=true")
    public EventList getFutureEvents() {
        List<Event> events = eventService.getFutureEvents(1);
        return eventMapper.convertEventsToDto(events);
    }

    @GetMapping(value = "", params = "onlyMeetings=true")
    public MeetingList getAllMeetings() {
        List<Meeting> meetings = eventService.getAllMeetings(1);
        return eventMapper.convertMeetingsToDto(meetings);
    }

    @GetMapping(value = "", params = { "onlyMeetings=true", "inFuture=true" })
    public MeetingList getFutureMeetings() {
        List<Meeting> meetings = eventService.getFutureMeetings(1);
        return eventMapper.convertMeetingsToDto(meetings);
    }

    @PostMapping("")
    public EventDto scheduleEvent(@RequestBody @Valid EventRequest eventRequest) {
        Event event = eventService.scheduleEventAtSynagogueOfMember(1, eventRequest);
        return eventMapper.convertEventToDto(event);
    }

    @PostMapping(value = "", params = "meeting=true")
    public MeetingDto scheduleMeeting(@RequestBody @Valid MeetingRequest meetingRequest) {
        Meeting meeting = eventService.scheduleMeetingAtSynagogueOfMember(1, meetingRequest);
        return eventMapper.convertMeetingToDto(meeting);
    }

    @PostMapping(value = "/{meetingId}/reserveSeat", params = "meeting=true")
    public boolean reserveSeatAtMeeting(@PathVariable("meetingId") long meetingId) {
        return eventService.reserveSeatAtMeeting(1, meetingId);
    }
}
