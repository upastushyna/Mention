package com.mention.beans;

import com.mention.model.User;
import com.mention.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUsers {
  @Bean
  public CommandLineRunner createUsersInDb(UserRepository userRepository) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        userRepository.save(new User("admin", "admin@gmail.com", "ADMIN"));
        userRepository.save(new User("alex", "alex@gmail.com", "ALEX1"));
        userRepository.save(new User("dima", "dima@gmail.com", "DIMA2"));
      }
    };
  }

}
