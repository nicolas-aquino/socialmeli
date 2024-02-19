package org.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.socialmeli.entity.User;

@AllArgsConstructor
@Data
public class UserDTO {
    private Integer user_id;
    private String user_name;

    public UserDTO(User user) {
        this.user_id = user.getUserId();
        this.user_name = user.getUserName();
    }
}
