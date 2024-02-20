package org.socialmeli.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.socialmeli.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Repository
public class ClientRepositoryImp implements IRepository<Client> {
    private List<Client> clients = new ArrayList<>();
    private VendorRepositoryImp vendorRepositoryImp;

    public ClientRepositoryImp(){
        vendorRepositoryImp = new VendorRepositoryImp();
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        Client client4 = new Client();

        client1.setUserName("Juan Perez");
        client2.setUserName("María García");
        client3.setUserName("Luis Rodríguez");
        client4.setUserName("Pepe Giménez");

        this.save(client1);
        this.save(client2);
        this.save(client3);
        this.save(client4);
    }

    private Integer autoIncrementId() {
        return clients.size() + 1;
    }

    public List<Client> findAll() {
        return clients;
    }

    public Integer save(Client client) {
        client.setUserId(autoIncrementId());
        clients.add(client);
        return client.getUserId();
    }

    public Client findOne(Integer id) {
        return clients.stream()
                .filter(client -> client.getUserId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteOne(Integer id) {
        clients.removeIf(c -> c.getUserId().equals(id));
    }
}