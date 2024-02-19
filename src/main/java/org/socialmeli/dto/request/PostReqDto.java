package org.socialmeli.dto.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.socialmeli.entity.Product;

import lombok.Data;

@Data
public class PostReqDto {

        private Integer userId;
        private LocalDate date;
        private Product product;
        private Integer category;
        private Double price;

        public PostReqDto(Integer user_id, String date, Product product, Integer category,
                        Double price) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                this.date = LocalDate.parse(date, formatter);
                this.userId = user_id;
                this.product = product;
                this.category = category;
                this.price = price;
        }

        public LocalDate date() {
                return date;
        }

        public Integer userId() {
                return userId;
        }

        public Product product() {
                return product;
        }

        public Integer category() {
                return category;
        }

        public Double price() {
                return price;
        }

}