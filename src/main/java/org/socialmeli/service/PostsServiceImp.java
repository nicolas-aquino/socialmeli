package org.socialmeli.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.socialmeli.dto.request.PostReqDto;
import org.socialmeli.dto.response.FollowedListDto;
import org.socialmeli.dto.response.PostDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Post;
import org.socialmeli.entity.Product;
import org.socialmeli.entity.Vendor;
import org.socialmeli.exception.BadRequestException;
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
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public FollowedListDto getFollowedList(Integer id, String order) {
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
                            p.getCategory(), p.getPrice()));
                        }
                    }
                }
            }
        }
        if (postDtoList.isEmpty()) {
            throw new NotFoundException("No hay posteos realizados por los vendedores que sigue el usuario las últimas dos semanas.");
        }
        // Ordena el ArrayList de posteos:
        if (order.equals("date_asc")) {
            Collections.sort(postDtoList, Comparator.comparing(PostDto::date));
        }
        else if (order.equals("date_desc")) {
            Collections.sort(postDtoList, Comparator.comparing(PostDto::date, Comparator.reverseOrder()));
        }
        else {
            throw new BadRequestException("Indicación de ordenamiento no válida. La misma tiene que ser \"date_asc\" o \"date_desc\"");
        }
        return new FollowedListDto(id, postDtoList);
    }

    @Override
    public PostReqDto savePost(PostReqDto postDto) {
       // postRepositoryImp.save(mapper.convertValue(postDto, Post.class));
       Post post = new Post(postDto.userId(), postDto.getDate(), postDto.getProduct(), postDto.getCategory(), postDto.getPrice());
       for(Post p : postRepositoryImp.findAll()){
        if(p.getProduct().getProductId().intValue() == post.getProduct().getProductId().intValue()){
            throw new BadRequestException("Ya existe un producto con el mismo id registrado.");
        }
       }
       postRepositoryImp.save(post);
        return postDto;
    }
}
