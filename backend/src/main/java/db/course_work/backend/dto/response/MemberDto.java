package db.course_work.backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
    private long id;
    private String name;
    private String surname;
    private String role;
    private String login;
    private SynagogueDto synagogue;
}
