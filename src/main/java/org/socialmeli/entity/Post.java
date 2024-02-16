package org.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer postId;
    private Integer userId;
    private LocalDate date;
    private Product product;
    private Integer category;
    private Double price;
}