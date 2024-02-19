package org.socialmeli.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.socialmeli.entity.Vendor;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Repository
public class VendorRepositoryImp implements IRepository<Vendor> {
    private List<Vendor> vendors;

    public VendorRepositoryImp(){
        Vendor vendor1 = new Vendor(1, "Fernando Gómez");
        Vendor vendor2 = new Vendor(2, "Alejandra Torres");
        Vendor vendor3 = new Vendor(3, "Javier Hernández");
        this.vendors = new ArrayList<>(List.of(vendor1, vendor2, vendor3));
    }

    public List<Vendor> findAll() {
        return vendors;
    }
    public void save(Vendor client) {
        vendors.add(client);
    }
    public Vendor findOne (Integer id) {
        return vendors.stream()
                .filter(client -> client.getUserId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void deleteOne(Integer id) {
        vendors.removeIf(c -> c.getUserId().equals(id));
    }
}
