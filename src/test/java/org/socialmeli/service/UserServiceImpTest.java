package org.socialmeli.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.repository.implementation.ClientRepositoryImp;
import org.socialmeli.repository.implementation.VendorRepositoryImp;
import org.socialmeli.service.implementation.UsersServiceImp;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {

    @Mock
    ClientRepositoryImp clientRepositoryImp;

    @Mock
    VendorRepositoryImp vendorRepositoryImp;

    @InjectMocks
    UsersServiceImp userServiceImp;

}
