package db.course_work.backend.dto.request;

import lombok.Data;

@Data
public class MemberRequest {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;
    private Long synagogueId;
}
