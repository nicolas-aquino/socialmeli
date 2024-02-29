package org.socialmeli.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.dto.request.FollowersListReqDto;
import org.socialmeli.dto.request.FollowingListReqDto;
import org.socialmeli.dto.request.UserFollowVendorDto;
import org.socialmeli.dto.response.MessageDto;
import org.socialmeli.dto.response.FollowersListDto;
import org.socialmeli.dto.response.FollowingListDto;
import org.socialmeli.exception.BadRequestException;
import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
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

    // T_0003
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

    // T_0004 & US_0003 & US_0008
    @Test
    @DisplayName("[T_0004] -> [US_0003] & [US_0008] - Happy path")
    void followersListOk() {
        // Arrange
        Integer vendorId = objectFactory.getValidVendorId();
        String order = objectFactory.getDescendentNameOrder();
        FollowersListReqDto followersListReqDto = new FollowersListReqDto(vendorId, order);
        FollowersListDto followersListDto = objectFactory.getVendorFollowersListDto();
        ResponseEntity<FollowersListDto> expected = new ResponseEntity<>(followersListDto, HttpStatus.OK);

        when(usersService.getFollowersList(followersListReqDto)).thenReturn(followersListDto);

        // Act
        ResponseEntity<?> result = usersController.followersList(vendorId, order);

        // Assert
        verify(usersService, atLeastOnce()).getFollowersList(followersListReqDto);
        assertEquals(result, expected);
    }

    // T_0004 & US_0004 & US_0008
    @Test
    @DisplayName("[T_0004] -> [US_0004] & [US_0008] - Happy path")
    void followingListOk() {
        // Arrange
        Integer clientId = objectFactory.getValidClientId();
        String order = objectFactory.getDescendentNameOrder();
        FollowingListReqDto followingListReqDto = new FollowingListReqDto(clientId, order);
        FollowingListDto followingListDto = objectFactory.getVendorsFollowingListDto();
        ResponseEntity<FollowingListDto> expected = new ResponseEntity<>(followingListDto, HttpStatus.OK);

        when(usersService.getFollowingList(followingListReqDto)).thenReturn(followingListDto);

        // Act
        ResponseEntity<?> result = usersController.followingList(clientId, order);

        // Assert
        verify(usersService, atLeastOnce()).getFollowingList(followingListReqDto);
        assertEquals(result, expected);
    }
}
