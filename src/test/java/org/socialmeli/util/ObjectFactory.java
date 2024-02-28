package org.socialmeli.util;

import org.socialmeli.dto.response.UserDto;
import org.socialmeli.dto.response.VendorFollowersListDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Vendor;

import java.util.List;

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
