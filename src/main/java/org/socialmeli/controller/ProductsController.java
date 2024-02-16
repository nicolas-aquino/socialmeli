package org.socialmeli.controller;

import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
import org.socialmeli.service.PostsServiceImp;
import org.socialmeli.service.UsersServiceImp;
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
    public RequestEntity<?>  createPost(/*@RequestBody PostDto postDto*/){
        //TODO:
        return null;
    }

    //products/followed/{userId}/list
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity vendorFollowedList(@PathVariable Integer userId){
        //TODO:

        return null;
    }
}
