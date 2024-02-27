package org.socialmeli.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.service.IPostsService;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductsControllerTest {

    @Mock
    private IPostsService postsService;
    @InjectMocks
    private ProductsController productsController;

}
