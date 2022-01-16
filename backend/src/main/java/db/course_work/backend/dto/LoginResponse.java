package db.course_work.backend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import db.course_work.backend.entities.Member;
import db.course_work.backend.serializers.LoginResponseSerializer;
import lombok.Data;

// { userToken: userToken, userData: {Member}
@JsonSerialize(using = LoginResponseSerializer.class)
@Data
public class LoginResponse {
    private String userToken;
    private Member member;

    public LoginResponse(String userToken, Member member) {
        this.member = member;
        this.userToken = userToken;
    }
}
