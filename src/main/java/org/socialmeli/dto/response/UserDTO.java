package org.socialmeli.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Integer user_id;
    String user_name;

    //public UserDTO(User user) {
    //    this.user_id = user.getUserId();
    //    this.user_name = user.getUserName();
    //}
}
