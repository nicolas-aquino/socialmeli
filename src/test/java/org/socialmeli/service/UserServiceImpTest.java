package org.socialmeli.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.dto.request.UserFollowVendorDto;
import org.socialmeli.dto.response.ExceptionDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Vendor;
import org.socialmeli.exception.BadRequestException;
import org.socialmeli.exception.NotFoundException;
import org.socialmeli.repository.implementation.ClientRepositoryImp;
import org.socialmeli.repository.implementation.VendorRepositoryImp;
import org.socialmeli.service.implementation.UsersServiceImp;
import org.socialmeli.util.ObjectFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

        when(clientRepositoryImp.findOne(userId)).thenReturn(null);

        when(vendorRepositoryImp.findOne(userId)).thenReturn(vendor1);
        when(vendorRepositoryImp.findOne(userIdToFollow)).thenReturn(vendor2);

        // Act
        userServiceImp.userFollowVendor(userFollowVendorDto);

        // Assert
        verify(vendorRepositoryImp, atLeastOnce()).findOne(userId);
        verify(vendorRepositoryImp, atLeastOnce()).findOne(userIdToFollow);
    }

    @Test
    @DisplayName("[T-0001] Sad path - User follows non-existing vendor")
    void vendorFollowsNonExistingUserTest() {
        // Arrange
        Client client = objectFactory.getValidClient();
        Integer userId = client.getUserId();
        Integer userIdToFollow = objectFactory.getInvalidUserId();
        UserFollowVendorDto userFollowVendorDto = new UserFollowVendorDto(userId, userIdToFollow);

        when(vendorRepositoryImp.findOne(userId)).thenReturn(null);

        when(clientRepositoryImp.findOne(userId)).thenReturn(client);

        // Act & Assert
        assertThrows(
                NotFoundException.class,
                () -> userServiceImp.userFollowVendor(userFollowVendorDto),
                "El vendedor no existe"
        );
    }

    @Test
    @DisplayName("[T-0001-OP] Sad path - User follows himself")
    void userFollowsHimselfTest() {
        // Arrange
        Client client = objectFactory.getValidClient();
        Integer userId = client.getUserId();
        UserFollowVendorDto userFollowVendorDto = new UserFollowVendorDto(userId, userId);

        // Act & Assertc
        assertThrows(
                BadRequestException.class,
                () -> userServiceImp.userFollowVendor(userFollowVendorDto),
                "Un usuario no se puede seguir a si mismo"
        );
    }

    @Test
    @DisplayName("[T-0001-OP] Sad path - Vendor already followed")
    void vendorAlreadyFollowedTest() {
        // Arrange
        Client client = objectFactory.getValidClientFollowingVendor();
        Vendor vendor = client.getFollowing().get(0);
        Integer userId = client.getUserId();
        Integer userIdToFollow = vendor.getUserId();
        UserFollowVendorDto userFollowVendorDto = new UserFollowVendorDto(userId, userIdToFollow);

        when(clientRepositoryImp.findOne(userId)).thenReturn(client);
        when(vendorRepositoryImp.findOne(userIdToFollow)).thenReturn(vendor);

        // Act & Assertc
        assertThrows(
                BadRequestException.class,
                () -> userServiceImp.userFollowVendor(userFollowVendorDto),
                "Ya se esta siguiendo al vendedor."
        );
    }
}
