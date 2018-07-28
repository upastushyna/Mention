package com.mention.dao;

import com.mention.model.Lyke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Lyke, Long> {
}
