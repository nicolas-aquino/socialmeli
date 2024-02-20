package org.socialmeli.dto.response;

import lombok.Data;
import org.socialmeli.dto.UserDTO;
import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.entity.Vendor;

import java.util.List;

@Data
public class VendorsFollowingListDto {

    Integer userId;
    String userName;
    List<UserDTO> vendors;

    public VendorsFollowingListDto(Integer userId, String userName, List<Vendor> vendors) {
        this.userId = userId;
        this.userName = userName;
        this.vendors = vendors
                .stream()
                .map(u -> new UserDTO(u.getUserId(), u.getUserName()))
                .toList();
    }
}
