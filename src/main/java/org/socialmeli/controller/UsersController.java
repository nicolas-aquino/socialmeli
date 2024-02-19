package org.socialmeli.controller;

import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.FollowSuccessDto;
import org.socialmeli.dto.response.FollowerCountDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
import org.socialmeli.service.PostsServiceImp;
import org.socialmeli.service.UsersServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private IPostsService postsService;
    private IUsersService usersService;

    public UsersController(PostsServiceImp postsService, UsersServiceImp usersService) {
        this.postsService = postsService;
        this.usersService = usersService;
    }

    /// US_0001
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@PathVariable Integer userId,
                                       @PathVariable Integer userIdToFollow){
        //TODO:
        usersService.userFollowVendor(userId,userIdToFollow);

        return new ResponseEntity<FollowSuccessDto>(new FollowSuccessDto("Vendedor seguido exitosamente"),HttpStatus.OK);

    }

    /// US_0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> followersCount(@PathVariable Integer userId){


        return  new ResponseEntity<FollowerCountDto>(usersService.vendorFollowersCount(userId),HttpStatus.OK);
    }

    //US_0003
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<VendorFollowersListDTO> followersList(@PathVariable UserIdDto userId){
        return new ResponseEntity<>(usersService.getFollowersList(userId), HttpStatus.OK);
    }

    //US_0004
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<VendorsFollowingListDto> followingList(@PathVariable UserIdDto userId){
        return new ResponseEntity<>(usersService.getFollowingList(userId), HttpStatus.OK);
    }

    // US_0007
    @PostMapping()
    public ResponseEntity<?> unfollowVendor(@PathVariable Integer userId,
                                            @PathVariable Integer userIdToUnFollow){
        //TODO:
        return null;
    }
}
