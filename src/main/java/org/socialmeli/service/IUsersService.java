package org.socialmeli.service;

import org.socialmeli.entity.User;

public interface IUsersService {

    User getUserById(Integer userId);
    void userFollowVendor(Integer userId, Integer vendorId);
}
