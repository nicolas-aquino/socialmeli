package org.socialmeli.service;

import org.socialmeli.dto.request.PostReqDto;
import org.socialmeli.dto.response.FollowedListDto;

public interface IPostsService {
    public FollowedListDto getFollowedList(Integer id, String order);
    public PostReqDto savePost(PostReqDto postDto);
}
