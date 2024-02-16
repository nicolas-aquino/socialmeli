package org.socialmeli.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

//@AllArgsConstructor
//@Data
//@FieldDefaults(level = AccessLevel.PRIVATE)
public record ExceptionDto(String message) {
    //String msg;
}
