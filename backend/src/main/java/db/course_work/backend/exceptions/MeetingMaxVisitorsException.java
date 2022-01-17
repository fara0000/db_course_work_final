package db.course_work.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Number of invited members exceeded")
public class MeetingMaxVisitorsException extends RuntimeException {
    public MeetingMaxVisitorsException() {
        super();
    }
}
