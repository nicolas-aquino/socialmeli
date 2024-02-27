package org.socialmeli.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.repository.implementation.ClientRepositoryImp;
import org.socialmeli.repository.implementation.PostRepositoryImp;
import org.socialmeli.repository.implementation.VendorRepositoryImp;
import org.socialmeli.service.implementation.PostsServiceImp;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostServiceImpTest {

    @Mock
    PostRepositoryImp postRepositoryImp;
    @Mock
    VendorRepositoryImp vendorRepositoryImp;
    @Mock
    ClientRepositoryImp clientRepositoryImp;
    @InjectMocks
    PostsServiceImp postsServiceImp;

}
