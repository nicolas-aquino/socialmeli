package org.socialmeli.service;

import org.socialmeli.dto.response.FollowedListDto;

public interface IPostsService {
    public FollowedListDto getFollowedList(Integer id, String order);
}
