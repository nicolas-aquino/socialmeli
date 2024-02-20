package org.socialmeli.controller;

import org.socialmeli.dto.request.*;
import org.socialmeli.dto.response.VendorFollowersListDTO;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.User;
import org.socialmeli.entity.Vendor;
import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
import org.socialmeli.service.PostsServiceImp;
import org.socialmeli.service.UsersServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.socialmeli.dto.response.FollowSuccessDto;
import org.socialmeli.dto.response.FollowerCountDto;
import org.socialmeli.dto.response.MessageDto;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    private IPostsService postsService;
    private IUsersService usersService;

    public UsersController(PostsServiceImp postsService, UsersServiceImp usersService) {
        this.postsService = postsService;
        this.usersService = usersService;
    }

    // US_0001
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@PathVariable Integer userId,
                                       @PathVariable Integer userIdToFollow){
        usersService.userFollowVendor(new UserFollowVendorDto(userId,userIdToFollow));
        return new ResponseEntity<FollowSuccessDto>(new FollowSuccessDto("Vendedor seguido exitosamente"),HttpStatus.OK);

    }

    // US_0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> followersCount(@PathVariable Integer userId){
        return  new ResponseEntity<FollowerCountDto>(usersService.vendorFollowersCount(new UserIdDto(userId)),HttpStatus.OK);
    }

    //US_0003
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<VendorFollowersListDTO> followersList(
            @PathVariable Integer userId,
            @RequestParam(required = false, defaultValue = "name_desc") String order){

        return new ResponseEntity<>(usersService.getFollowersList(new FollowersListReqDto(userId, order)), HttpStatus.OK);
    }

    //US_0004
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<VendorsFollowingListDto> followingList(
             @PathVariable Integer userId,
             @RequestParam(required = false, defaultValue = "name_desc") String order){

        return ResponseEntity.ok(usersService.getFollowingList(new FollowingListReqDto(userId, order)));
    }

    // US_0006
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<?> followedList(
            @PathVariable Integer userId,
            @RequestParam(required = false, defaultValue = "date_desc") String order){
        return new ResponseEntity<>(postsService.getFollowedList(new FollowedListReqDto(userId, order)), HttpStatus.OK);
    }

    // US_0007
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<MessageDto> unfollowVendor(@PathVariable Integer userId,
                                                     @PathVariable Integer userIdToUnfollow){
        return new ResponseEntity<>(usersService.unfollowVendor(new UserUnfollowVendorDTO(userId, userIdToUnfollow)), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> getusers() {
        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
    }
}
