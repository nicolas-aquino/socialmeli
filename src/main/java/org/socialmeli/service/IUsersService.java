package org.socialmeli.service;

import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.User;

public interface IUsersService {
    VendorFollowersListDTO getFollowersList(UserIdDto userIdDto);
    VendorsFollowingListDto getFollowingList(UserIdDto userIdDto);
    User getUserById(Integer userId);
    void userFollowVendor(Integer userId, Integer vendorId);
}


