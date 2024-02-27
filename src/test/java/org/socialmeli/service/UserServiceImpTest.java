package org.socialmeli.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.dto.request.UserFollowVendorDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Vendor;
import org.socialmeli.repository.implementation.ClientRepositoryImp;
import org.socialmeli.repository.implementation.VendorRepositoryImp;
import org.socialmeli.service.implementation.UsersServiceImp;
import org.socialmeli.util.ObjectFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {

    @Mock
    ClientRepositoryImp clientRepositoryImp;

    @Mock
    VendorRepositoryImp vendorRepositoryImp;

    @InjectMocks
    UsersServiceImp userServiceImp;

    ObjectFactory objectFactory = new ObjectFactory();

    // T-0001
    @Test
    @DisplayName("[T-0001] Happy path - Client follows existing vendor")
    void followUserOkTest() {
        // Arrange
        Client client = objectFactory.getValidClient();
        Vendor vendor = objectFactory.getValidVendor();
        Integer userId = client.getUserId();
        Integer userIdToFollow = vendor.getUserId();
        UserFollowVendorDto userFollowVendorDto = new UserFollowVendorDto(userId, userIdToFollow);

        when(clientRepositoryImp.findOne(userId)).thenReturn(client);
        when(vendorRepositoryImp.findOne(userIdToFollow)).thenReturn(vendor);

        // Act
        userServiceImp.userFollowVendor(userFollowVendorDto);

        // Assert
        verify(clientRepositoryImp, atLeastOnce()).findOne(userId);
        verify(vendorRepositoryImp, atLeastOnce()).findOne(userIdToFollow);
    }

    @Test
    @DisplayName("[T-0001] Happy path - Vendor follows existing vendor")
    void vendorFollowUserOkTest() {
        // Arrange
        Vendor vendor1 = objectFactory.getValidVendor();
        Vendor vendor2 = objectFactory.getValidVendor2();
        Integer userId = vendor1.getUserId();
        Integer userIdToFollow = vendor2.getUserId();
        UserFollowVendorDto userFollowVendorDto = new UserFollowVendorDto(userId, userIdToFollow);

        when(vendorRepositoryImp.findOne(userId)).thenReturn(vendor1);
        when(vendorRepositoryImp.findOne(userIdToFollow)).thenReturn(vendor2);

        // Act
        userServiceImp.userFollowVendor(userFollowVendorDto);

        // Assert
        verify(vendorRepositoryImp, atLeastOnce()).findOne(userId);
        verify(vendorRepositoryImp, atLeastOnce()).findOne(userIdToFollow);
    }


    
}
