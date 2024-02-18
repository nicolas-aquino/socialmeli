package org.socialmeli.dto.response;

import java.util.List;

public record FollowedListDto(Integer userId, List<PostDto> posts) {
}