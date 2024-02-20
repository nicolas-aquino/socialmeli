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
    private List<Client> clients;
    private VendorRepositoryImp vendorRepositoryImp;

    public ClientRepositoryImp(){
        this.clients = new ArrayList<>();
        this.vendorRepositoryImp = new VendorRepositoryImp();
        Client cliente1 = new Client(1,"Juan Perez");
        Client cliente2 = new Client(2, "María García");
        Client cliente3 = new Client(3, "Luis Rodríguez");
        //cliente1.getFollowing().add(vendorRepositoryImp.findAll().get(0));

        this.clients.add(cliente1);
        this.clients.add(cliente2);
        this.clients.add(cliente3);
    }

    public List<Client> findAll() {
        return clients;
    }
    public Integer save(Client client) {
        clients.add(client);
        return client.getUserId();
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