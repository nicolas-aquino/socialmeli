package org.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    protected Integer userId;
    protected String userName;
    protected List<Vendor> following;

    public User(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.following = new ArrayList<>();
    }
}
