package com.mention.dao;

import com.mention.model.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao {

    void addPost(Post post);

    Post getPost(Long id);

    void updatePost(Post post);

    void deletePost(Long id);

}
