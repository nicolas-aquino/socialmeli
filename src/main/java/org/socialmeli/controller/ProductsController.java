package org.socialmeli.controller;

import org.socialmeli.dto.request.PostReqDto;
import org.socialmeli.entity.Product;
import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
import org.socialmeli.service.PostsServiceImp;
import org.socialmeli.service.UsersServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private IPostsService postsService;
    private IUsersService usersService;

    public ProductsController(PostsServiceImp postsService, UsersServiceImp usersService) {
        this.postsService = postsService;
        this.usersService = usersService;
    }

    ///products/post
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostReqDto postDto){
        return new ResponseEntity<>(postsService.savePost(postDto), HttpStatus.OK);
    }

    //products/followed/{userId}/list
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity vendorFollowedList(@PathVariable Integer userId){
        //TODO:

        return null;
    }
}
