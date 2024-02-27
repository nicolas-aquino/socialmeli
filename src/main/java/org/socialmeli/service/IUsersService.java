package org.socialmeli.service;

import org.socialmeli.dto.request.*;
import org.socialmeli.dto.response.FollowerCountDto;
import org.socialmeli.dto.response.MessageDto;
import org.socialmeli.dto.response.VendorFollowersListDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;


public interface IUsersService {
    VendorFollowersListDto getFollowersList(FollowersListReqDto req);
    VendorsFollowingListDto getFollowingList(FollowingListReqDto req);
    void userFollowVendor(UserFollowVendorDto req);
    FollowerCountDto vendorFollowersCount(UserIdDto userIdDto);
    MessageDto unfollowVendor(UserUnfollowVendorDto req);
}


