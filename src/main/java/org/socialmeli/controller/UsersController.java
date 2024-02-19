package org.socialmeli.controller;

import org.socialmeli.dto.request.UserIdDto;
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
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public RequestEntity<?> followUser(@PathVariable Integer userId,
                                       @PathVariable Integer userIdToFollow){
        //TODO:
        return null;
    }

    ///users/{userId}/followers/count
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> followersCount(@PathVariable Integer userId){
        //TODO:
        return null;
    }

    ///users/{userId}/followers/list
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> followersList(
            @PathVariable Integer userId){
        //TODO:
        return ResponseEntity.ok(usersService.getFollowersList(userId));
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
