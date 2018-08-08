package org.danit.mention.service;

import org.danit.mention.dto.PostDtoRs;

import java.util.List;

public interface UserPostsService {

  List<PostDtoRs> getFollowedPosts(String username);

  List<PostDtoRs> getPostsByUsername(String username);
}
