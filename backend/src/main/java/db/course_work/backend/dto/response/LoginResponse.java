package db.course_work.backend.dto.response;

import lombok.Data;

// { userToken: userToken, userData: {Member}


@Data
public class LoginResponse {
    private String userToken;
    private Long memberId;

    public LoginResponse(String userToken, Long memberId) {
        this.memberId = memberId;
        this.userToken = userToken;
    }
}
