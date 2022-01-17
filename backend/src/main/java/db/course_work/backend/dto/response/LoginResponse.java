package db.course_work.backend.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import db.course_work.backend.entities.Member;
import db.course_work.backend.serializers.LoginResponseSerializer;
import lombok.Data;

// { userToken: userToken, userData: {Member}
@JsonSerialize(using = LoginResponseSerializer.class)
@Data
public class LoginResponse {
    private String userToken;
    private MemberDto member;

    public LoginResponse(String userToken, MemberDto member) {
        this.userToken = userToken;
        this.member = member;
    }
}