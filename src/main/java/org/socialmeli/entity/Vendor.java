package org.socialmeli.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class Vendor extends User {
    private List<User> followers;

    public Vendor() {
        this.followers = new ArrayList<>();
    }
}

