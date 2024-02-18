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
        this.vendorRepositoryImp = new VendorRepositoryImp();
        Client cliente1 = new Client(1,"Juan Perez");
        Client cliente2 = new Client(2, "María García");
        Client cliente3 = new Client(3, "Luis Rodríguez");
        cliente1.setFollowing(List.of(vendorRepositoryImp.findAll().get(0), vendorRepositoryImp.findAll().get(1)));
        this.clients = new ArrayList<>(List.of(cliente1, cliente2, cliente3));
    }

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