package com.mention.dao;

import com.mention.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDao extends JpaRepository<Chat, Long> {

}
