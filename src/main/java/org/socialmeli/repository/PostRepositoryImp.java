package org.socialmeli.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.socialmeli.entity.Post;
import org.socialmeli.entity.Product;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Repository
public class PostRepositoryImp implements IRepository<Post> {
    private List<Post> posts;
    private VendorRepositoryImp vendorRepositoryImp;

    
    public PostRepositoryImp(){
        vendorRepositoryImp = new VendorRepositoryImp();
        Product product1 = new Product(1, "Camiseta", "Ropa", "Nike", "Blanco", "Con logo");
        Product product2 = new Product(2, "Zapatos", "Calzado", "Adidas", "Negro", "N/A");
        Product product3 = new Product(3, "Bolso", "Accesorio", "Guess", "Rojo", "Cuero");
        Post post1 = new Post(1, 101, LocalDate.of(2022, 3, 20), product1, 1, 35.99, this.vendorRepositoryImp.findAll().get(0));
        Post post2 = new Post(2, 102, LocalDate.now(), product2, 2, 79.99, this.vendorRepositoryImp.findAll().get(0));
        Post post3 = new Post(3, 103, LocalDate.now(), product3, 1, 49.99, this.vendorRepositoryImp.findAll().get(1));
        this.posts = new ArrayList<>(List.of(post1, post2, post3));
    }

    public List<Post> findAll() {
        return posts;
    }
    public void save(Post client) {
        posts.add(client);
    }
    public Post findOne (Integer id) {
        return posts.stream()
                .filter(client -> client.getPostId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void deleteOne(Integer id) {
        posts.removeIf(c -> c.getPostId().equals(id));
    }
}
