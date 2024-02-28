package org.socialmeli.util;

import org.socialmeli.dto.response.UserDto;
import org.socialmeli.dto.response.VendorFollowersListDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Post;
import org.socialmeli.entity.Product;
import org.socialmeli.entity.Vendor;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ObjectFactory {

    public Client getValidClient() {
        Client res = new Client();
        res.setUserId(getValidUserId());
        res.setUserName("Test Man");
        return res;
    }

    public Client getValidClient2() {
        Client res = new Client();
        res.setUserId(getValidUserId());
        res.setUserName("Martin Colo");
        return res;
    }

    public Vendor getValidVendor() {
        Vendor res = new Vendor();
        res.setUserId(getValidVendorId());
        res.setUserName("Git Man");
        return res;
    }

    public Vendor getValidVendor2() {
        Vendor res = new Vendor();
        res.setUserId(getValidVendorId2());
        res.setUserName("G Man");
        return res;
    }

    public Client getValidClientFollowingVendor() {
        Client res = getValidClient();
        Vendor vendor = getValidVendor();
        res.getFollowing().add(vendor);
        vendor.getFollowers().add(res);
        return res;
    }

    public Vendor getValidVendorFollowingVendor() {
        Vendor follower = getValidVendor();
        Vendor followingVendor = getValidVendor2();
        follower.getFollowing().add(followingVendor);
        followingVendor.getFollowers().add(follower);
        return follower;
    }

    public Integer getValidUserId() {
        return 1;
    }

    public Integer getValidUserId2() {
        return 2;
    }

    public Integer getValidClientId() {
        return 6; //TODO Revisar ids de clientes
    }

    public Integer getValidVendorId() {
        return 2;
    }

    public Integer getValidVendorId2() {
        return 3;
    }

    public Integer getInvalidUserId() {
        return 99999;
    }

    public String getValidNameOrder() {
        return "name_desc";
    }

    public String getInvalidOrder() {
        return "invalido";
    }

    public List<Post> getNewPostList(Vendor vendor) {
        List<Post> postList = new ArrayList<>();
        Product product1 = new Product(1, "Camiseta", "Ropa", "Nike", "Blanco", "Con logo");
        Product product2 = new Product(2, "Zapatos", "Calzado", "Adidas", "Negro", "N/A");
        Post post1 = new Post(vendor.getUserId(), LocalDate.now(), product1, 1, 35.99);
        Post post2 = new Post(vendor.getUserId(), LocalDate.of(2023, 3, 20), product2, 2, 79.99);
        postList.add(post1);
        postList.add(post2);
        return postList;
    }

    public String getAscendentNameOrder() {
        return "name_asc";
    }

    public List<Post> getOldPostList(Vendor vendor) {
        List<Post> postList = new ArrayList<>();
        Product product1 = new Product(1, "Camiseta", "Ropa", "Nike", "Blanco", "Con logo");
        Product product2 = new Product(2, "Zapatos", "Calzado", "Adidas", "Negro", "N/A");
        Post post1 = new Post(vendor.getUserId(), LocalDate.of(2023, 3, 20), product1, 1, 35.99);
        Post post2 = new Post(vendor.getUserId(), LocalDate.of(2023, 3, 20), product2, 2, 79.99);
        postList.add(post1);
        postList.add(post2);
        return postList;
    }
    public VendorFollowersListDto getVendorFollowersListDto() {
        Vendor vendor = getValidVendor();
        Client client1 = getValidClient();
        Client client2 = getValidClient2();
        return new VendorFollowersListDto(
                vendor.getUserId(),
                vendor.getUserName(),
                List.of(new UserDto(client1.getUserId(), client1.getUserName()), new UserDto(client2.getUserId(), client2.getUserName()))
        );
    }

    public VendorsFollowingListDto getVendorsFollowingListDto() {
        Client client = getValidClient();
        Vendor vendor1 = getValidVendor();
        Vendor vendor2 = getValidVendor2();
        return new VendorsFollowingListDto(
                client.getUserId(),
                client.getUserName(),
                List.of(new UserDto(vendor1.getUserId(), vendor1.getUserName()), new UserDto(vendor2.getUserId(), vendor2.getUserName()))
        );
    }
}
