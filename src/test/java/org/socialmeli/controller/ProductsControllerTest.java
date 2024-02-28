package org.socialmeli.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.dto.request.FollowedListReqDto;
import org.socialmeli.dto.response.FollowedListDto;
import org.socialmeli.service.IPostsService;
import org.socialmeli.util.ObjectFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductsControllerTest {

    @Mock
    private IPostsService postsService;
    @InjectMocks
    private ProductsController productsController;

    ObjectFactory objectFactory = new ObjectFactory();
    
    // T-0008
    @Test
    public void followedList(){
        // Arrange
        Integer userId = objectFactory.getValidUserId();
        String order = "date_asc";
        FollowedListReqDto followedListReqDto = new FollowedListReqDto(userId, order);
        // Act
        ResponseEntity<FollowedListDto> result = productsController.followedList(userId, order);
        // Assert
        verify(postsService, atLeastOnce()).getFollowedList(followedListReqDto);
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }
}
