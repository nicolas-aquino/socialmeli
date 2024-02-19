package org.socialmeli.service;

import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;

public interface IUsersService {
    VendorFollowersListDTO getFollowersList(Integer userId);
    VendorsFollowingListDto getFollowingList(UserIdDto userIdDto);
}


