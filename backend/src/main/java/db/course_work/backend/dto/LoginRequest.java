package db.course_work.backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
