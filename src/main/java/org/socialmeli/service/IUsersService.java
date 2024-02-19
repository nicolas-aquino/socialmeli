package org.socialmeli.service;

import org.socialmeli.dto.VendorFollowersListDTO;

public interface IUsersService {
    VendorFollowersListDTO getFollowersList(Integer userId);
}
