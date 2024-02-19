package org.socialmeli.dto.response;

import java.time.LocalDate;

import org.socialmeli.entity.Product;

public record PostDto(
        Integer postId,
        Integer userId,
        LocalDate date,
        ProductDto product,
        Integer category,
        Double price

) {
        public LocalDate date() {
                return date;
        }

        public Integer postId() {
                return postId;
        }

        public Integer userId() {
                return userId;
        }

        public ProductDto product() {
                return product;
        }

        public Integer category() {
                return category;
        }

        public Double price() {
                return price;
        }      
        
        
}