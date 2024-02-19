package org.socialmeli.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.socialmeli.entity.Vendor;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@Repository
public class VendorRepositoryImp implements IRepository<Vendor> {
    private List<Vendor> vendors;

    public VendorRepositoryImp(){

        this.vendors = new ArrayList<>();
        Vendor vendor1 = new Vendor(4, "Fernando Gómez");
        Vendor vendor2 = new Vendor(5, "Alejandra Torres");
        Vendor vendor3 = new Vendor(6, "Javier Hernández");

        this.vendors.add(vendor1);
        this.vendors.add(vendor2);
        this.vendors.add(vendor3);

        //this.vendors = new ArrayList<>(List.of(vendor1, vendor2, vendor3));
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
