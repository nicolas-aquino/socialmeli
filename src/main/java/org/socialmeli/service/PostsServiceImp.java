package org.socialmeli.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.socialmeli.dto.response.FollowedListDto;
import org.socialmeli.dto.response.PostDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Post;
import org.socialmeli.entity.Vendor;
import org.socialmeli.exception.NotFoundException;
import org.socialmeli.repository.ClientRepositoryImp;
import org.socialmeli.repository.PostRepositoryImp;
import org.socialmeli.repository.VendorRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsServiceImp implements IPostsService {
    @Autowired
    PostRepositoryImp postRepositoryImp;
    @Autowired
    VendorRepositoryImp vendorRepositoryImp;
    @Autowired
    ClientRepositoryImp clientRepositoryImp;

    @Override
    public FollowedListDto getFollowedList(Integer id) {
        // Busca cliente por ID:
        Client client = new Client();
        for (Client c : this.clientRepositoryImp.findAll()) {
            if (c.getUserId().intValue() == id.intValue()) {
                client = c;
            }
        }
        if (client.getUserId() == null) {
            throw new NotFoundException("No se encontró ningun cliente en el sistema con el ID indicado.");
        }

        // Busca vendedores seguidos cliente:
        List<Vendor> vendorList = this.postRepositoryImp.getFollowedList(client, this.vendorRepositoryImp.findAll());
        List<PostDto> postDtoList = new ArrayList<>();
        if (vendorList.isEmpty()) {
            throw new NotFoundException("El usuario ingresado no sigue a ningun vendedor.");
        } else {
            // Busca posteos de vendedores que cumplan con los requisitos:
            for (Vendor v : vendorList) {
                for (Post p : postRepositoryImp.findAll()) {
                    if (p.getUserId().intValue() == v.getUserId().intValue()) {
                        if (p.getDate().isAfter(LocalDate.now().minusWeeks(2))) {
                            postDtoList.add(new PostDto(p.getPostId(), p.getUserId(), p.getDate(), p.getProduct(),
                            p.getCategory(), p.getPrice()));                        }
                    }
                }
            }
        }
        if (postDtoList.isEmpty()) {
            throw new NotFoundException("No hay posteos realizados por los vendedores que sigue el usuario las últimas dos semanas.");
        }
        return new FollowedListDto(id, postDtoList);
    }
}
