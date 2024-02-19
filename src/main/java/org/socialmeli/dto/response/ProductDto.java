package org.socialmeli.dto.response;

public record ProductDto(Integer productId,
        String productName,
        String type,
        String brand,
        String color,
        String notes) {
}
