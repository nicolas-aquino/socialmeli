package org.socialmeli.service;

import org.socialmeli.dto.request.PostReqDto;
import org.socialmeli.dto.response.FollowedListDto;
import org.socialmeli.dto.response.PostIdDto;

public interface IPostsService {
    public FollowedListDto getFollowedList(Integer id, String order);
    public PostIdDto savePost(PostReqDto postDto);
}
