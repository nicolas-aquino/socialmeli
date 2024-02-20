package org.socialmeli.dto.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.socialmeli.entity.Product;

import lombok.Data;

@Data
@AllArgsConstructor
public class PostReqDto {

        private Integer userId;
        @JsonFormat(pattern = "dd-MM-yyyy")
        private LocalDate date;
        private Product product;
        private Integer category;
        private Double price;

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