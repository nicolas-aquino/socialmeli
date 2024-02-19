package org.socialmeli.entity;

import java.util.ArrayList;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Client extends User {

    public Client(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.following = new ArrayList<>();
    }
}