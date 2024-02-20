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

    public Post(Integer userId, LocalDate date, Product product, Integer category, Double price) {
        this.userId = userId;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
    }
}
