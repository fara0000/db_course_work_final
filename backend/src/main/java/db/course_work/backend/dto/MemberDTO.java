package db.course_work.backend.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;
    private Integer synagogueId;
}
