package org.socialmeli.entity;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor extends User {
    private List<User> followers;
}

