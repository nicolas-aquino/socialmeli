package org.socialmeli.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.socialmeli.dto.request.FollowedListReqDto;
import org.socialmeli.dto.response.FollowedListDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Post;
import org.socialmeli.entity.Vendor;
import org.socialmeli.exception.BadRequestException;
import org.socialmeli.exception.NotFoundException;
import org.socialmeli.repository.implementation.ClientRepositoryImp;
import org.socialmeli.repository.implementation.PostRepositoryImp;
import org.socialmeli.repository.implementation.VendorRepositoryImp;
import org.socialmeli.service.implementation.PostsServiceImp;
import org.socialmeli.util.ObjectFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    ObjectFactory objectFactory = new ObjectFactory();

    // T-0008
    @Test
    @DisplayName("[T-0008] Sad path - No hay usuario registrado con el id indicado")
    void getFollowedListUserNotRegisterTest() {
        // Arrange:
        String order = "date_asc";
        Integer id = 1;
        when(clientRepositoryImp.findOne(id)).thenReturn(null);
        when(vendorRepositoryImp.findOne(id)).thenReturn(null);
        // Act && Assert:
        String mess = assertThrows(
                NotFoundException.class,
                () -> postsServiceImp.getFollowedList(new FollowedListReqDto(id, order))).getMessage();
        verify(clientRepositoryImp, atLeastOnce()).findOne(id);
        verify(vendorRepositoryImp, atLeastOnce()).findOne(id);
        assertEquals("No se encontró ningun usuario en el sistema con el ID indicado.", mess);
    }

    // T-0008
    @Test
    @DisplayName("[T-0008] Sad path - El usuario ingresado no sigue a ningun vendedor")
    void getFollowedListUserNotFollowVendorTest() {
        // Arrange:
        Client client = objectFactory.getValidClient();
        String order = "date_asc";
        List<Vendor> emptyVendorList = new ArrayList<>();
        when(clientRepositoryImp.findOne(client.getUserId())).thenReturn(client);
        when(vendorRepositoryImp.findOne(1)).thenReturn(null);
        when(vendorRepositoryImp.findAll()).thenReturn(emptyVendorList);
        when(postRepositoryImp.getFollowedList(client, emptyVendorList)).thenReturn(emptyVendorList);
        // Act && Assert:
        String mess = assertThrows(
                NotFoundException.class,
                () -> postsServiceImp.getFollowedList(new FollowedListReqDto(client.getUserId(), order))).getMessage();
        verify(clientRepositoryImp, atLeastOnce()).findOne(client.getUserId());
        verify(vendorRepositoryImp, atLeastOnce()).findOne(1);
        assertEquals("El usuario ingresado no sigue a ningun vendedor.", mess);
    }

    // T-0008
    @Test
    @DisplayName("[T-0008] Sad path - No hay posteos realizados por los vendedores.")
    void getFollowedListEmptyPostListTest() {
        // Arrange:
        Client client = objectFactory.getValidClient();
        String order = "date_asc";
        List<Post> emptyPostList = new ArrayList<>();
        List<Vendor> vendorList = List.of(objectFactory.getValidVendor());
        when(clientRepositoryImp.findOne(client.getUserId())).thenReturn(client);
        when(vendorRepositoryImp.findOne(1)).thenReturn(null);
        when(vendorRepositoryImp.findAll()).thenReturn(vendorList);
        when(postRepositoryImp.getFollowedList(client, vendorList)).thenReturn(vendorList);
        when(postRepositoryImp.getPostsByUserId(objectFactory.getValidVendor().getUserId())).thenReturn(emptyPostList);
        // Act && Assert:
        String exMesage = assertThrows(
                NotFoundException.class,
                () -> postsServiceImp.getFollowedList(new FollowedListReqDto(client.getUserId(), order))).getMessage();
        verify(clientRepositoryImp, atLeastOnce()).findOne(client.getUserId());
        assertEquals("No hay posteos realizados por los vendedores que sigue el usuario las últimas dos semanas.",
                exMesage);
    }

    // T-0008
    @Test
    @DisplayName("[T-0008] Happy path")
    void getFollowedListOkTest() {
        // Arrange:
        Client client = objectFactory.getValidClient();
        client.setUserId(1);
        Vendor vendor = objectFactory.getValidVendor();
        String order = "date_asc";
        List<Vendor> vendorList = List.of(objectFactory.getValidVendor());
        when(clientRepositoryImp.findOne(client.getUserId())).thenReturn(client);
        when(vendorRepositoryImp.findOne(1)).thenReturn(null);
        when(vendorRepositoryImp.findAll()).thenReturn(vendorList);
        when(postRepositoryImp.getFollowedList(client, vendorList)).thenReturn(vendorList);
        when(postRepositoryImp.getPostsByUserId(vendor.getUserId())).thenReturn(objectFactory.getPostTwoWeeksAway(vendor));
        // Act:
        FollowedListDto followersList = postsServiceImp
                .getFollowedList(new FollowedListReqDto(client.getUserId(), order));
        // Assert:
        assertEquals(2, followersList.getPosts().size());
        assertEquals(1, followersList.getUserId());
    }

    // T-0008
    @Test
    @DisplayName("[T-0008] Sad path - No hay posteos realizados por los vendedores que sigue el usuario las últimas dos semanas.")
    void getFollowedListNoPostTest() {
        // Arrange:
        Client client = objectFactory.getValidClient();
        client.setUserId(1);
        Vendor vendor = objectFactory.getValidVendor();
        String order = "date_asc";
        List<Vendor> vendorList = List.of(objectFactory.getValidVendor());
        when(clientRepositoryImp.findOne(client.getUserId())).thenReturn(client);
        when(vendorRepositoryImp.findOne(1)).thenReturn(null);
        when(vendorRepositoryImp.findAll()).thenReturn(vendorList);
        when(postRepositoryImp.getFollowedList(client, vendorList)).thenReturn(vendorList);
        when(postRepositoryImp.getPostsByUserId(vendor.getUserId())).thenReturn(objectFactory.getOldPostList(vendor));
        // Act && Assert:
        String exMesage = assertThrows(
                NotFoundException.class,
                () -> postsServiceImp.getFollowedList(new FollowedListReqDto(client.getUserId(), order))).getMessage();
        verify(clientRepositoryImp, atLeastOnce()).findOne(client.getUserId());
        assertEquals("No hay posteos realizados por los vendedores que sigue el usuario las últimas dos semanas.",
                exMesage);
    }

    // T-0006
    @Test
    @DisplayName("[T-0006] Happy path - Ordena de forma ascendente")
    void getFollowedListOrdenAscOkTest() {
       // Arrange:
       Client client = objectFactory.getValidClient();
       client.setUserId(1);
       Vendor vendor = objectFactory.getValidVendor();
       String order = "date_asc";
       List<Vendor> vendorList = List.of(objectFactory.getValidVendor());
       when(clientRepositoryImp.findOne(client.getUserId())).thenReturn(client);
       when(vendorRepositoryImp.findOne(1)).thenReturn(null);
       when(vendorRepositoryImp.findAll()).thenReturn(vendorList);
       when(postRepositoryImp.getFollowedList(client, vendorList)).thenReturn(vendorList);
       when(postRepositoryImp.getPostsByUserId(vendor.getUserId())).thenReturn(objectFactory.getPostTwoWeeksAway(vendor));
       // Act:
       FollowedListDto followersList = postsServiceImp.getFollowedList(new FollowedListReqDto(client.getUserId(), order));
       // Assert:
       assertEquals(LocalDate.now().minusDays(2), followersList.getPosts().get(0).getDate());
       assertEquals(LocalDate.now(), followersList.getPosts().get(1).getDate());
    }
    
    // T-0006
    @Test
    @DisplayName("[T-0006] Happy path - Ordena de forma ascendente")
    void getFollowedListOrdenDescOkTest() {
        // Arrange:
        Client client = objectFactory.getValidClient();
        client.setUserId(1);
        Vendor vendor = objectFactory.getValidVendor();
        String order = "date_desc";
        List<Vendor> vendorList = List.of(objectFactory.getValidVendor());
        when(clientRepositoryImp.findOne(client.getUserId())).thenReturn(client);
        when(vendorRepositoryImp.findOne(1)).thenReturn(null);
        when(vendorRepositoryImp.findAll()).thenReturn(vendorList);
        when(postRepositoryImp.getFollowedList(client, vendorList)).thenReturn(vendorList);
        when(postRepositoryImp.getPostsByUserId(vendor.getUserId())).thenReturn(objectFactory.getPostTwoWeeksAway(vendor));
        // Act:
        FollowedListDto followersList = postsServiceImp.getFollowedList(new FollowedListReqDto(client.getUserId(), order));
        // Assert:
        assertEquals(LocalDate.now(), followersList.getPosts().get(0).getDate());
        assertEquals(LocalDate.now().minusDays(2), followersList.getPosts().get(1).getDate());
    }
    
    // T-0006
    @Test
    @DisplayName("[T-0006] Sad path - indicación de orden no valida")
    void getFollowedListNotValidOrdenTest() {
        // Arrange:
        Client client = objectFactory.getValidClient();
        client.setUserId(1);
        Vendor vendor = objectFactory.getValidVendor();
        String order = "date_descd";
        List<Vendor> vendorList = List.of(objectFactory.getValidVendor());
        when(clientRepositoryImp.findOne(client.getUserId())).thenReturn(client);
        when(vendorRepositoryImp.findOne(1)).thenReturn(null);
        when(vendorRepositoryImp.findAll()).thenReturn(vendorList);
        when(postRepositoryImp.getFollowedList(client, vendorList)).thenReturn(vendorList);
        when(postRepositoryImp.getPostsByUserId(vendor.getUserId())).thenReturn(objectFactory.getPostTwoWeeksAway(vendor));
        // Act && Assert:
        String exMesage = assertThrows(
                BadRequestException.class,
                () -> postsServiceImp.getFollowedList(new FollowedListReqDto(client.getUserId(), order))).getMessage();
        assertEquals("Indicación de ordenamiento no válida. La misma tiene que ser \"date_asc\" o \"date_desc\"",
                exMesage);
        }
}
