package org.socialmeli.dto.request;

public class UserIdDto {
    private Integer userId;

    public UserIdDto(String userId) {
        this.userId = Integer.parseInt(userId);
    }

    public Integer getUserId() {
        return userId;
    }
}
