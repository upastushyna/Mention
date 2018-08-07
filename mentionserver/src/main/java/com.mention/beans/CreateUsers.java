package com.mention.beans;

import com.mention.model.Chat;
import com.mention.model.Comment;
import com.mention.model.Favorite;
import com.mention.model.Follow;
import com.mention.model.Like;
import com.mention.model.Message;
import com.mention.model.Post;
import com.mention.model.Profile;
import com.mention.model.User;
import com.mention.repository.ChatRepository;
import com.mention.repository.CommentRepository;
import com.mention.repository.FavoriteRepository;
import com.mention.repository.FollowRepository;
import com.mention.repository.LikeRepository;
import com.mention.repository.MessageRepository;
import com.mention.repository.PostRepository;
import com.mention.repository.ProfileRepository;
import com.mention.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class CreateUsers {
  @Bean
  public CommandLineRunner createUsersInDb(UserRepository userRepository, PostRepository postRepository,
                                           CommentRepository commentRepository, LikeRepository likeRepository,
                                           FavoriteRepository favoriteRepository, MessageRepository messageRepository,
                                           ChatRepository chatRepository, FollowRepository followRepository,
                                           ProfileRepository profileRepository) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        userRepository.save(new User("admin", "admin@gmail.com", "ADMIN", true)); //1
        userRepository.save(new User("alex", "alex@gmail.com", "ALEX1", true)); //2
        userRepository.save(new User("dima", "dima@gmail.com", "DIMA2", true)); //3
        userRepository.save(new User("yarik", "yarik@gmail.com", "YARIK", true)); //4
        userRepository.save(new User("superman", "havenoidea@gmail.com", "amazing", true)); //5

        postRepository.save(new Post("My amazing post!", userRepository.findByUsername("alex").get())); //6
        postRepository.save(new Post("Something new!", userRepository.findByUsername("dima").get())); //7
        postRepository.save(new Post("Call Batman", userRepository.findByUsername("superman").get()));  //8
        postRepository.save(new Post("Lorem ipsum", userRepository.findByUsername("admin").get()));  //9
        postRepository.save(new Post("Hi there", userRepository.findByUsername("yarik").get()));  //10

        commentRepository.save(new Comment("Not Bad", userRepository.findByUsername("superman").get(),
            postRepository.findById(6L).get())); //11
        commentRepository.save(new Comment("Comment", userRepository.findByUsername("yarik").get(),
            postRepository.findById(8L).get()));    //12
        commentRepository.save(new Comment("New Post", userRepository.findByUsername("admin").get(),
            postRepository.findById(10L).get()));    //13
        commentRepository.save(new Comment("ipsum Lorem", userRepository.findByUsername("dima").get(),
            postRepository.findById(9L).get()));   //14
        commentRepository.save(new Comment("Really?", userRepository.findByUsername("alex").get(),
            postRepository.findById(7L).get()));   //15

        likeRepository.save(new Like(
            userRepository.findByUsername("superman").get(), postRepository.findById(6L).get()));    //16
        likeRepository.save(new Like(
            userRepository.findByUsername("superman").get(), postRepository.findById(7L).get()));    //17
        likeRepository.save(new Like(
            userRepository.findByUsername("admin").get(), postRepository.findById(8L).get()));   //18
        likeRepository.save(new Like(
            userRepository.findByUsername("admin").get(), postRepository.findById(10L).get()));   //19
        likeRepository.save(new Like(
            userRepository.findByUsername("yarik").get(), postRepository.findById(7L).get()));   //20
        likeRepository.save(new Like(
            userRepository.findByUsername("dima").get(), postRepository.findById(6L).get()));    //21
        likeRepository.save(new Like(
            userRepository.findByUsername("alex").get(), postRepository.findById(7L).get()));    //22
        likeRepository.save(new Like(
            userRepository.findByUsername("alex").get(), postRepository.findById(9L).get()));    //23
        likeRepository.save(new Like(
            userRepository.findByUsername("alex").get(), postRepository.findById(8L).get()));    //24
        likeRepository.save(new Like(
            userRepository.findByUsername("admin").get(), postRepository.findById(10L).get()));    //25

        favoriteRepository.save(new Favorite(
            userRepository.findByUsername("dima").get(), postRepository.findById(6L).get()));  //26
        favoriteRepository.save(new Favorite(
            userRepository.findByUsername("alex").get(), postRepository.findById(7L).get()));    //27
        favoriteRepository.save(new Favorite(
            userRepository.findByUsername("admin").get(), postRepository.findById(8L).get()));   //28
        favoriteRepository.save(new Favorite(
            userRepository.findByUsername("yarik").get(), postRepository.findById(9L).get()));   //29
        favoriteRepository.save(new Favorite(
            userRepository.findByUsername("superman").get(), postRepository.findById(10L).get()));   //30

        chatRepository.save(new Chat(
            userRepository.findByUsername("superman").get(), userRepository.findByUsername("yarik").get()));   //31
        chatRepository.save(new Chat(
            userRepository.findByUsername("admin").get(), userRepository.findByUsername("alex").get()));   //32
        chatRepository.save(new Chat(
            userRepository.findByUsername("yarik").get(), userRepository.findByUsername("admin").get()));    //33
        chatRepository.save(new Chat(
            userRepository.findByUsername("dima").get(), userRepository.findByUsername("alex").get()));    //34
        chatRepository.save(new Chat(
            userRepository.findByUsername("alex").get(), userRepository.findByUsername("admin").get()));   //35

        messageRepository.save(new Message(
            "Hello!", userRepository.findByUsername("superman").get(),
            userRepository.findByUsername("yarik").get(), chatRepository.findById(31L).get()));
        messageRepository.save(new Message(
            "How are you?", userRepository.findByUsername("admin").get(),
            userRepository.findByUsername("alex").get(), chatRepository.findById(32L).get()));
        messageRepository.save(new Message(
            "What you want?", userRepository.findByUsername("yarik").get(),
            userRepository.findByUsername("admin").get(), chatRepository.findById(33L).get()));
        messageRepository.save(new Message(
            "Where is my money b*tch!?", userRepository.findByUsername("dima").get(),
            userRepository.findByUsername("alex").get(), chatRepository.findById(34L).get()));
        messageRepository.save(new Message(
            "I'm fine", userRepository.findByUsername("alex").get(),
            userRepository.findByUsername("admin").get(), chatRepository.findById(35L).get()));

        followRepository.save(new Follow(
            userRepository.findByUsername("superman").get(), userRepository.findByUsername("dima").get()));
        followRepository.save(new Follow(
            userRepository.findByUsername("admin").get(), userRepository.findByUsername("alex").get()));
        followRepository.save(new Follow(
            userRepository.findByUsername("yarik").get(), userRepository.findByUsername("admin").get()));
        followRepository.save(new Follow(
            userRepository.findByUsername("dima").get(), userRepository.findByUsername("yarik").get()));
        followRepository.save(new Follow(
            userRepository.findByUsername("alex").get(), userRepository.findByUsername("superman").get()));
        followRepository.save(new Follow(
            userRepository.findByUsername("admin").get(), userRepository.findByUsername("dima").get()));
        followRepository.save(new Follow(
            userRepository.findByUsername("admin").get(), userRepository.findByUsername("superman").get()));
        followRepository.save(new Follow(
            userRepository.findByUsername("admin").get(), userRepository.findByUsername("yarik").get()));


        //https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100&ssl=1

        profileRepository.save(new Profile(null, null, null,
            null,
            "https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100&ssl=1",
            null, userRepository.findByUsername("superman").get()));
        profileRepository.save(new Profile(null, null, null,
            null,
            "https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100&ssl=1",
            null, userRepository.findByUsername("admin").get()));
        profileRepository.save(new Profile(null, null, null,
            null,
            "https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100&ssl=1",
            null, userRepository.findByUsername("yarik").get()));
        profileRepository.save(new Profile(null, null, null,
            null,
            "https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100&ssl=1",
            null, userRepository.findByUsername("dima").get()));
        profileRepository.save(new Profile(null, null, null,
            null,
            "https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100&ssl=1",
            null, userRepository.findByUsername("alex").get()));
      }
    };
  }

}
