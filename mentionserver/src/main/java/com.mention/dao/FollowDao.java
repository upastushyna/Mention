package com.mention.dao;

import com.mention.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowDao extends JpaRepository<Follow, Long> {
}
