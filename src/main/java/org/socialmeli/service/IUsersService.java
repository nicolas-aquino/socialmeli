package org.socialmeli.service;

import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;

public interface IUsersService {
    VendorsFollowingListDto getFollowingList(UserIdDto userIdDto);
}
