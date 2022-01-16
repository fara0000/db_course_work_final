package db.course_work.backend.dto;

import lombok.Data;

// { userToken: userToken, userData: {Member}


@Data
public class LoginResponse {
    private String userToken;
    private Integer memberId;

    public LoginResponse(String userToken, Integer memberId) {
        this.memberId = memberId;
        this.userToken = userToken;
    }
}
