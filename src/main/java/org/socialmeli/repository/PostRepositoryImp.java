package org.socialmeli.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Post;

import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class PostRepositoryImp implements IRepository<Post> {
    private List<Post> posts;

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
