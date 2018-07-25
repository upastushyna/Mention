package com.mention.dao;

import com.mention.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteDao extends JpaRepository<Favorite, Long> {
}
