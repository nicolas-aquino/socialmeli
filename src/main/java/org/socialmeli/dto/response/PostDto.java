package org.socialmeli.dto.response;

import java.time.LocalDate;

import org.socialmeli.entity.Product;

public record PostDto(
        Integer postId,
        Integer userId,
        LocalDate date,
        Product product,
        Integer category,
        Double price

) {
        public LocalDate date() {
                return date;
        }       
}