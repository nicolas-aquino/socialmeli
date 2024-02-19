package org.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.socialmeli.entity.Vendor;

import java.util.List;


@AllArgsConstructor
@Data
public class VendorFollowersListDTO {
    private Integer user_id;
    private String user_name;
    private List<UserDTO> followers;

    public VendorFollowersListDTO(Vendor vendor) {
        this.user_id = vendor.getUserId();
        this.user_name = vendor.getUserName();
        this.followers = vendor.getFollowers()
                .stream()
                .map(u -> new UserDTO(u.getUserId(), u.getUserName()))
                .toList();
    }
}
