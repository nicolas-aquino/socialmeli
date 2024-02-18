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
        this.userId = userId;
        this.userName = userName;
        this.following = new ArrayList<>();;
        this.followers = new ArrayList<>();
    }
}
