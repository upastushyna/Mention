package com.mention.beans;

import com.mention.model.Like;
import com.mention.model.Post;
import com.mention.model.User;
import com.mention.repository.LikeRepository;
import com.mention.repository.PostRepository;
import com.mention.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUsers {
  @Bean
  public CommandLineRunner createUsersInDb(UserRepository userRepository, PostRepository postRepository, LikeRepository likeRepository) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        userRepository.save(new User("admin", "admin@gmail.com", "ADMIN"));
        userRepository.save(new User("alex", "alex@gmail.com", "ALEX1"));
        userRepository.save(new User("dima", "dima@gmail.com", "DIMA2"));
        userRepository.save(new User("yarik", "yarik@gmail.com", "YARIK"));
        userRepository.save(new User("superman", "havenoidea@gmail.com", "amazing"));

        postRepository.save(new Post("My amazing post!", userRepository.findByUsername("alex").get()));
        postRepository.save(new Post("Something new!", userRepository.findByUsername("dima").get()));
        postRepository.save(new Post("Call Batman", userRepository.findByUsername("superman").get()));
        postRepository.save(new Post("Lorem inpsum", userRepository.findByUsername("admin").get()));
        postRepository.save(new Post("Hi there", userRepository.findByUsername("yarik").get()));

      }
    };
  }

}
