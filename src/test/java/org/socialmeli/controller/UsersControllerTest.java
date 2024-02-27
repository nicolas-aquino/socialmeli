package org.socialmeli.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.dto.request.FollowersListReqDto;
import org.socialmeli.exception.BadRequestException;
import org.socialmeli.exception.NotFoundException;
import org.socialmeli.service.IPostsService;
import org.socialmeli.service.IUsersService;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Mock
    private IPostsService postsService;
    @Mock
    private IUsersService usersService;
    @InjectMocks
    UsersController usersController;

    @Test
    void invalidOrder() {
        String ordenamientoInvalido = "invalido";
        Integer idVendedorValido = 1;
        FollowersListReqDto a = new FollowersListReqDto(idVendedorValido, ordenamientoInvalido);

        when(usersService.getFollowersList(a))
                .thenThrow(new BadRequestException(""));

       // assertThrows(usersController.followersList(idVendedorValido, ordenamientoInvalido));
    }
}
