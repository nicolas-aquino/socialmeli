package org.socialmeli.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
public class Vendor extends User{
    private List<User> followers;

    public Vendor(Integer userId, String userName) {
        super(userId, userName);
        this.followers = new ArrayList<>();
    }
}

