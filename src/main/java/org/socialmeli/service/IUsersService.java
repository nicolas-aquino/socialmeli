package org.socialmeli.service;

import org.socialmeli.dto.request.FollowersListReqDto;
import org.socialmeli.dto.request.FollowingListReqDto;
import org.socialmeli.dto.response.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.FollowerCountDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.User;
import org.socialmeli.dto.response.MessageDto;


public interface IUsersService {
    VendorFollowersListDTO getFollowersList(FollowersListReqDto req);
    VendorsFollowingListDto getFollowingList(FollowingListReqDto req);
    User getUserById(Integer userId);
    void userFollowVendor(Integer userId, Integer vendorId);
    FollowerCountDto vendorFollowersCount(Integer userId);
    MessageDto unfollowVendor(UserIdDto userId, UserIdDto vendorId);
}


