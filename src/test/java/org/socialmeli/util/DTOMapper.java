package org.socialmeli.util;

import org.socialmeli.dto.response.UserDto;
import org.socialmeli.dto.response.VendorFollowersListDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.User;
import org.socialmeli.entity.Vendor;

import java.util.List;

public final class DTOMapper {
    public static VendorsFollowingListDto toVendorsFollowingList(Integer userId, String userName, List<Vendor> vendors) {
        List<UserDto> vendorsNew = vendors
                .stream()
                .map(u -> new UserDto(u.getUserId(), u.getUserName()))
                .toList();
        return new VendorsFollowingListDto(userId, userName, vendorsNew);
    }

    public static VendorFollowersListDto toVendorFollowersList(Vendor vendor) {
        Integer userId = vendor.getUserId();
        String userName = vendor.getUserName();
        List<UserDto> followers = vendor.getFollowers()
                .stream()
                .map(u -> new UserDto(u.getUserId(), u.getUserName()))
                .toList();
        return new VendorFollowersListDto(userId, userName, followers);
    }

    public static VendorFollowersListDto toVendorFollowersList(Vendor vendor, List<User> followersList) {
        Integer userId = vendor.getUserId();
        String userName = vendor.getUserName();
        List<UserDto> followers = followersList
                .stream()
                .map(u -> new UserDto(u.getUserId(), u.getUserName()))
                .toList();
        return new VendorFollowersListDto(userId, userName, followers);
    }
}
