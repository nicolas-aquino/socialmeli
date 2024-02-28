package org.socialmeli.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.dto.request.FollowersListReqDto;
import org.socialmeli.dto.request.FollowingListReqDto;
import org.socialmeli.dto.request.UserFollowVendorDto;
import org.socialmeli.dto.request.UserUnfollowVendorDto;
import org.socialmeli.dto.response.MessageDto;
import org.socialmeli.dto.response.VendorFollowersListDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.exception.BadRequestException;
import org.socialmeli.dto.response.VendorFollowersListDto;
import org.socialmeli.entity.User;
import org.socialmeli.entity.Vendor;
import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
import org.socialmeli.util.DTOMapper;
import org.socialmeli.util.ObjectFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Mock
    private IPostsService postsService;
    @Mock
    private IUsersService usersService;
    @InjectMocks
    UsersController usersController;

    ObjectFactory objectFactory = new ObjectFactory();

    // T-0001
    @Test
    @DisplayName("[T-0001] Happy path")
    void followUserOk() {
        // Arrange
        Integer userId = objectFactory.getValidUserId();
        Integer userIdToFollow = objectFactory.getValidVendorId();
        UserFollowVendorDto userFollowVendorDto = new UserFollowVendorDto(userId, userIdToFollow);
        ResponseEntity<MessageDto> expected = ResponseEntity.ok(new MessageDto("Vendedor seguido exitosamente"));

        doNothing().when(usersService).userFollowVendor(userFollowVendorDto);

        // Act
        ResponseEntity<MessageDto> result = usersController.followUser(userId, userIdToFollow);

        // Assert
        verify(usersService, atLeastOnce()).userFollowVendor(userFollowVendorDto);
        assertEquals(result, expected);
    }

    // T-0003
    @Test
    void invalidOrderOk() {
        // Arrange
        String ordenamientoInvalido = objectFactory.getInvalidOrder();
        Integer idVendedorValido = objectFactory.getValidVendorId();
        FollowersListReqDto a = new FollowersListReqDto(idVendedorValido, ordenamientoInvalido);
        String expectedErrorMessage = "El ordenamiento pedido es inválido";
        BadRequestException expectedException = new BadRequestException(expectedErrorMessage);

        when(usersService.getFollowersList(a)).thenThrow(expectedException);

        // Act and Assert
        assertThrows(
                BadRequestException.class,
                () -> {
                    usersController.followersList(idVendedorValido, ordenamientoInvalido);
                },
                expectedErrorMessage);
    }

    // T-0004
    @Test
    void followersListOk() {
        // Arrange
        Integer vendorId = objectFactory.getValidVendorId();
        String order = objectFactory.getValidNameOrder();
        FollowersListReqDto followersListReqDto = new FollowersListReqDto(vendorId, order);
        VendorFollowersListDto vendorFollowersListDto = objectFactory.getVendorFollowersListDto();
        ResponseEntity<VendorFollowersListDto> expected = new ResponseEntity<>(vendorFollowersListDto, HttpStatus.OK);

        when(usersService.getFollowersList(followersListReqDto)).thenReturn(vendorFollowersListDto);

        // Act
        ResponseEntity<?> result = usersController.followersList(vendorId, order);

        // Assert
        verify(usersService, atLeastOnce()).getFollowersList(followersListReqDto);
        assertEquals(result, expected);
    }

    // T-0004
    @Test
    void followingListOk() {
        // Arrange
        Integer clientId = objectFactory.getValidClientId();
        String order = objectFactory.getValidNameOrder();
        FollowingListReqDto followingListReqDto = new FollowingListReqDto(clientId, order);
        VendorsFollowingListDto vendorsFollowingListDto = objectFactory.getVendorsFollowingListDto();
        ResponseEntity<VendorsFollowingListDto> expected = new ResponseEntity<>(vendorsFollowingListDto, HttpStatus.OK);

        when(usersService.getFollowingList(followingListReqDto)).thenReturn(vendorsFollowingListDto);

        // Act
        ResponseEntity<?> result = usersController.followingList(clientId, order);

        // Assert
        verify(usersService, atLeastOnce()).getFollowingList(followingListReqDto);
        assertEquals(result, expected);
    }
}
