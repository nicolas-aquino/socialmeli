package org.socialmeli.service;

import org.socialmeli.dto.request.*;
import org.socialmeli.dto.response.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserUnfollowVendorDTO;
import org.socialmeli.dto.response.FollowerCountDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.Client;
import org.socialmeli.dto.response.MessageDto;

import java.util.List;


public interface IUsersService {
    VendorFollowersListDTO getFollowersList(FollowersListReqDto req);
    VendorsFollowingListDto getFollowingList(FollowersListReqDto req);
    void userFollowVendor(UserFollowVendorDto req);
    FollowerCountDto vendorFollowersCount(UserIdDto userIdDto);
    MessageDto unfollowVendor(UserUnfollowVendorDTO req);
    List<Client> getAll();
}


