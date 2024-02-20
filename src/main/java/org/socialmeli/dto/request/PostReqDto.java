package org.socialmeli.dto.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.socialmeli.entity.Product;

import lombok.Data;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostReqDto {
        Integer userId;
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate date;
        Product product;
        Integer category;
        Double price;
}