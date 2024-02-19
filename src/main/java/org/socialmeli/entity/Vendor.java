package org.socialmeli.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Vendor extends User{
    private List<User> followers;

    public Vendor(Integer userId, String userName) {
        super(userId, userName);
        this.followers = new ArrayList<>();
    }
}
