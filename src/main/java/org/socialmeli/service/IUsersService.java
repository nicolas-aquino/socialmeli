package org.socialmeli.service;

import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.FollowerCountDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.User;
import org.socialmeli.dto.response.MessageDto;


public interface IUsersService {
    VendorFollowersListDTO getFollowersList(UserIdDto userIdDto, String order);
    VendorsFollowingListDto getFollowingList(UserIdDto userIdDto, String order);
    User getUserById(Integer userId);
    void userFollowVendor(Integer userId, Integer vendorId);
    FollowerCountDto vendorFollowersCount(Integer userId);
    MessageDto unfollowVendor(UserIdDto userId, UserIdDto vendorId);
}


