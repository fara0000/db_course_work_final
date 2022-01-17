package db.course_work.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "the scheduled time has already passed")
public class SchedulingEventException extends RuntimeException {
    public SchedulingEventException() {
        super();
    }
}
