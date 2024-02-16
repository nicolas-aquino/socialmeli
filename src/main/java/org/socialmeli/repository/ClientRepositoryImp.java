package org.socialmeli.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.socialmeli.entity.Client;

import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class ClientRepositoryImp implements IRepository<Client> {
    private List<Client> clients;

    public List<Client> findAll() {
        return clients;
    }
    public void save(Client client) {
        clients.add(client);
    }
    public Client findOne (Integer id) {
        return clients.stream()
                .filter(client -> client.getUserId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void deleteOne(Integer id) {
        clients.removeIf(c -> c.getUserId().equals(id));
    }
}