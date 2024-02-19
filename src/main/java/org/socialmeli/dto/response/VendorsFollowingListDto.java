package org.socialmeli.dto.response;

import org.socialmeli.entity.Vendor;

import java.util.List;

public record VendorsFollowingListDto(Integer userId, List<Vendor> vendors) {
}
