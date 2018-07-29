package com.mention.dao;

import com.mention.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesDao extends JpaRepository<Likes, Long> {
}
