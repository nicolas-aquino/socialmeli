package org.socialmeli.util;

import org.socialmeli.entity.Client;
import org.socialmeli.entity.User;
import org.socialmeli.entity.Vendor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ObjectFactory {

    public Client getValidClient() {
        Client res = new Client();
        res.setUserId(getValidUserId());
        res.setUserName("Test Man");
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

    public String getInvalidOrder() {
        return "invalido";
    }


}
