package org.socialmeli.repository;

import org.socialmeli.entity.Client;
import org.socialmeli.entity.Vendor;
import org.socialmeli.repository.implementation.VendorRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorRepositoryImpTest {

    @Autowired
    VendorRepositoryImp vendorRepositoryImp;

    //Tests unitarios del repositorio
    
    /*
     * @Test
    @DisplayName("")
    void testFindAll() {
        List<Vendor> allVendors = vendorRepositoryImp.findAll();
        assertNotNull(allVendors);
        assertEquals(5, allVendors.size());
    }
     */

    @Test
    @DisplayName("fffff")
    void testSave() {
    Integer expectedId = 5;
    Vendor vendor = new Vendor();

    Integer savedUserId = vendorRepositoryImp.save(vendor);

    assertNotNull(savedUserId);
    assertNotEquals(expectedId, savedUserId);
    }

    @Test
    @DisplayName("jkjkjk")
    void testFindOne() {
    Vendor vendor = new Vendor();
    vendor.setUserName("Test User");

    Integer savedUserId = vendorRepositoryImp.save(vendor);

    Vendor foundVendor = vendorRepositoryImp.findOne(savedUserId);

    assertNotNull(foundVendor);
    assertEquals(savedUserId, foundVendor.getUserId());
    }

    @Test
    @DisplayName("asasas")
    void testDeleteOne() {
    Vendor vendor = new Vendor();
    vendor.setUserName("Test User");

    Integer savedUserId = vendorRepositoryImp.save(vendor);

    vendorRepositoryImp.deleteOne(savedUserId);

    Vendor deletedVendor = vendorRepositoryImp.findOne(savedUserId);

    assertNull(deletedVendor);
    }
}
