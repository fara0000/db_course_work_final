package db.course_work.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User with provided id not found")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }
}
