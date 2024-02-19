package org.socialmeli.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.socialmeli.entity.Post;
import org.socialmeli.entity.Vendor;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Repository
public class VendorRepositoryImp implements IRepository<Vendor> {
    private List<Vendor> vendors;

    public VendorRepositoryImp() {
        this.vendors = new ArrayList<>();
        vendors.add(new Vendor(1, "Juan Perez"));
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
