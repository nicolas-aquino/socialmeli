package org.socialmeli.util;

import org.socialmeli.entity.Client;
import org.socialmeli.entity.Vendor;

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

    public Client getValidFollowingList() {
        //List<Vendor> following = new ArrayList<>();
        //following.add(new Vendor(1, "Fernando Gomez", ));
        return null;
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

    public String getInvalidOrder() {
        return "invalido";
    }

}
