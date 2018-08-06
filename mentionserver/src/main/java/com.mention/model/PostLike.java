package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "postLikes")
public class PostLike {

    @Id
    @Column(name = "post_like_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    @JsonIgnoreProperties(value = {"profile", "postLikes", "posts", "postComments", "chats", "favorites"})
    private User user;


    @ManyToOne
    @JoinColumn(name = "post_id", updatable = false)
    @JsonIgnoreProperties(value = {"author", "postLikes", "postComments", "favorites"})
    private Post post;

    protected PostLike() {
    }

    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}

