package org.socialmeli.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.socialmeli.dto.request.*;
import org.socialmeli.dto.response.*;
import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
import org.socialmeli.service.implementation.PostsServiceImp;
import org.socialmeli.service.implementation.UsersServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@Validated
public class UsersController {

    private IPostsService postsService;
    private IUsersService usersService;

    public UsersController(PostsServiceImp postsService, UsersServiceImp usersService) {
        this.postsService = postsService;
        this.usersService = usersService;
    }

    // US_0001
    @PostMapping("/{userId}/follow/{userIdToFollow}")

    public ResponseEntity<MessageDto> followUser(
            @PathVariable
            @Min(value = 1, message = "El ID debe ser mayor que 0")
            Integer userId,

            @PathVariable
            @Min(value = 1, message = "El ID a seguir debe ser mayor que 0")
            Integer userIdToFollow)
    {
        usersService.userFollowVendor(new UserFollowVendorDto(userId,userIdToFollow));
        return new ResponseEntity<>(new MessageDto("Vendedor seguido exitosamente"), HttpStatus.OK);

    }

    // US_0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowerCountDto> followersCount(
            @PathVariable
            @Min(value = 1, message = "El ID debe ser mayor que 0")
            Integer userId)
    {
        return  new ResponseEntity<>(usersService.vendorFollowersCount(new UserIdDto(userId)),HttpStatus.OK);
    }

    //US_0003
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<VendorFollowersListDto> followersList(
            @PathVariable Integer userId,
            @RequestParam(required = false, defaultValue = "name_desc") String order) {

        return new ResponseEntity<>(usersService.getFollowersList(new FollowersListReqDto(userId, order)), HttpStatus.OK);
    }

    //US_0004
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<VendorsFollowingListDto> followingList(
             @PathVariable Integer userId,
             @RequestParam(required = false, defaultValue = "name_desc") String order) {

        return ResponseEntity.ok(usersService.getFollowingList(new FollowingListReqDto(userId, order)));
    }

    // US_0007
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<MessageDto> unfollowVendor(@PathVariable Integer userId,
                                                     @PathVariable Integer userIdToUnfollow) {
        return new ResponseEntity<>(usersService.unfollowVendor(new UserUnfollowVendorDto(userId, userIdToUnfollow)), HttpStatus.OK);
    }
}
