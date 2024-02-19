package org.socialmeli.controller;

import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.FollowSuccessDto;
import org.socialmeli.dto.response.FollowerCountDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
import org.socialmeli.service.PostsServiceImp;
import org.socialmeli.service.UsersServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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

    /// users/{userId}/follow/{userIdToFollow}
    //US_001
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@PathVariable Integer userId,
                                       @PathVariable Integer userIdToFollow){

        usersService.userFollowVendor(userId,userIdToFollow);

        return new ResponseEntity<FollowSuccessDto>(new FollowSuccessDto("Vendedor seguido exitosamente"),HttpStatus.OK);

    }

    ///users/{userId}/followers/count
    //US_002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> followersCount(@PathVariable Integer userId){


        return  new ResponseEntity<FollowerCountDto>(usersService.vendorFollowersCount(userId),HttpStatus.OK);
    }

    ///users/{userId}/followers/list
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<VendorsFollowingListDto> followersList(@PathVariable UserIdDto userId){
        return new ResponseEntity<>(usersService.getFollowingList(userId), HttpStatus.OK);
    }

    ///users/{userId}/followed/list?order=
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<?> followedList(@PathVariable UserIdDto userId, @RequestParam(required = false, defaultValue = "date_desc") String order){
        return new ResponseEntity<>(postsService.getFollowedList(userId.getUserId(), order), HttpStatus.OK);
    }

    ///users/{userId}/unfollow/{userIdToUnfollow}
    @PostMapping()
    public ResponseEntity<?> unfollowVendor(@PathVariable Integer userId,
                                            @PathVariable Integer userIdToUnFollow){
        //TODO:
        return null;
    }
}
