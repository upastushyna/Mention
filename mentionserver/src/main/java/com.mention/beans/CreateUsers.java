package com.mention.beans;


import com.mention.config.EmailService;
import com.mention.model.Chat;
import com.mention.model.Comment;
import com.mention.model.CommentLike;
import com.mention.model.Follow;
import com.mention.model.Message;
import com.mention.model.Post;
import com.mention.model.PostLike;
import com.mention.model.Profile;
import com.mention.model.User;
import com.mention.repository.ChatRepository;
import com.mention.repository.CommentLikeRepository;
import com.mention.repository.CommentRepository;
import com.mention.repository.FollowRepository;
import com.mention.repository.MessageRepository;
import com.mention.repository.PostLikeRepository;
import com.mention.repository.PostRepository;
import com.mention.repository.ProfileRepository;
import com.mention.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CreateUsers {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  public CommandLineRunner createUsersInDb(UserRepository userRepository,
                                           PostRepository postRepository,
                                           CommentRepository commentRepository,
                                           PostLikeRepository postLikeRepository,
                                           MessageRepository messageRepository,
                                           ChatRepository chatRepository,
                                           FollowRepository followRepository,
                                           ProfileRepository profileRepository,
                                           CommentLikeRepository commentLikeRepository) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {

        userRepository.save(new User("admin", "admin@gmail.com", passwordEncoder.encode("ADMIN44"), true)); //1
        userRepository.save(new User("alex", "alex@gmail.com", passwordEncoder.encode("ALEX1222"), true)); //2
        userRepository.save(new User("dima", "dima@gmail.com", passwordEncoder.encode("DIMA2112"), true)); //3
        userRepository.save(new User("yarik", "yarik@gmail.com", passwordEncoder.encode("YARIK228"), true)); //4
        userRepository.save(new User("superman", "havenoidea@gmail.com", passwordEncoder.encode("amazing"), true)); //5

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

        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("superman").get(), postRepository.findById(6L).get()));    //16
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("superman").get(), postRepository.findById(7L).get()));    //17
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("admin").get(), postRepository.findById(8L).get()));   //18
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("admin").get(), postRepository.findById(10L).get()));   //19
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("yarik").get(), postRepository.findById(7L).get()));   //20
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("dima").get(), postRepository.findById(6L).get()));    //21
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("alex").get(), postRepository.findById(7L).get()));    //22
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("alex").get(), postRepository.findById(9L).get()));    //23
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("alex").get(), postRepository.findById(8L).get()));    //24
        postLikeRepository.save(new PostLike(
            userRepository.findByUsername("admin").get(), postRepository.findById(9L).get()));    //25

        commentRepository.save(new Comment("Good job", userRepository.findByUsername("superman").get(),
            postRepository.findById(10L).get())); //26
        commentRepository.save(new Comment("Thanks!", userRepository.findByUsername("yarik").get(),
            postRepository.findById(10L).get()));    //27
        commentRepository.save(new Comment("Bullshit!", userRepository.findByUsername("admin").get(),
            postRepository.findById(10L).get()));    //28
        commentRepository.save(new Comment("Hahahahaha", userRepository.findByUsername("dima").get(),
            postRepository.findById(10L).get()));   //29
        commentRepository.save(new Comment("Really?", userRepository.findByUsername("alex").get(),
            postRepository.findById(10L).get()));   //30

        chatRepository.save(new Chat(
            userRepository.findByUsername("superman").get(), userRepository.findByUsername("yarik").get()));   //31
        chatRepository.save(new Chat(
            userRepository.findByUsername("admin").get(), userRepository.findByUsername("alex").get()));   //32
        chatRepository.save(new Chat(
            userRepository.findByUsername("yarik").get(), userRepository.findByUsername("admin").get()));    //33
        chatRepository.save(new Chat(
            userRepository.findByUsername("dima").get(), userRepository.findByUsername("alex").get()));    //34
        chatRepository.save(new Chat(
            userRepository.findByUsername("alex").get(), userRepository.findByUsername("superman").get()));   //35

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
            "https://c1.staticflickr.com/9/8086/8466271529_dc5c0a958f_z.jpg",
            "https://www.50-best.com/images/twitter_backgrounds/black_and_white_city_twitter_background.jpg",
            userRepository.findByUsername("superman").get()));
        profileRepository.save(new Profile(null, null, null,
            null,
            "https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100&ssl=1",
            "https://www.50-best.com/images/twitter_backgrounds/new_york_city_twitter_background.jpg",
            userRepository.findByUsername("admin").get()));
        profileRepository.save(new Profile(null, null, null,
            null,
            "https://www.shareicon.net/download/2016/11/09/851666_user_512x512.png",
            "https://i.pinimg.com/originals/2c/84/0e/2c840e86d494c5e809f850b00a69ad29.jpg",
            userRepository.findByUsername("yarik").get()));
        profileRepository.save(new Profile(null, null, null,
            null,
            "http://panificadoramarcelo.cl/wp-content/uploads/2016/07/default-avatar-tech-guy.png",
            "http://www.twitterbackgroundsize.com/wp-content/uploads/2016/05/Twitter-Backgrounds-and-Covers.jpg",
            userRepository.findByUsername("dima").get()));
        profileRepository.save(new Profile(null, null, null,
            null,
            "http://i63.tinypic.com/fymsnt.png",
            "https://images.template.net/wp-content/uploads/2014/11/twitter-background.jpg",
            userRepository.findByUsername("alex").get()));

        messageRepository.save(new Message(
            "What's up?", userRepository.findByUsername("superman").get(),
            userRepository.findByUsername("yarik").get(), chatRepository.findById(31L).get()));
        messageRepository.save(new Message(
            "How are you?", userRepository.findByUsername("yarik").get(),
            userRepository.findByUsername("superman").get(), chatRepository.findById(31L).get()));
        messageRepository.save(new Message(
            "cho tipa kak?", userRepository.findByUsername("yarik").get(),
            userRepository.findByUsername("admin").get(), chatRepository.findById(33L).get()));
        messageRepository.save(new Message(
            "Where is my money b*tch!?", userRepository.findByUsername("admin").get(),
            userRepository.findByUsername("yarik").get(), chatRepository.findById(33L).get()));
        messageRepository.save(new Message(
            "Where are you?", userRepository.findByUsername("yarik").get(),
            userRepository.findByUsername("superman").get(), chatRepository.findById(31L).get()));
        messageRepository.save(new Message(
            "Как дела?", userRepository.findByUsername("superman").get(),
            userRepository.findByUsername("yarik").get(), chatRepository.findById(31L).get()));

        commentLikeRepository.save(new CommentLike(userRepository.findByUsername("yarik").get(),
            commentRepository.findById(11L).get()));
        commentLikeRepository.save(new CommentLike(userRepository.findByUsername("superman").get(),
            commentRepository.findById(11L).get()));
        commentLikeRepository.save(new CommentLike(userRepository.findByUsername("admin").get(),
            commentRepository.findById(11L).get()));
        commentLikeRepository.save(new CommentLike(userRepository.findByUsername("yarik").get(),
            commentRepository.findById(12L).get()));
        commentLikeRepository.save(new CommentLike(userRepository.findByUsername("superman").get(),
            commentRepository.findById(12L).get()));
        commentLikeRepository.save(new CommentLike(userRepository.findByUsername("admin").get(),
            commentRepository.findById(12L).get()));
        commentLikeRepository.save(new CommentLike(userRepository.findByUsername("yarik").get(),
            commentRepository.findById(13L).get()));
        commentLikeRepository.save(new CommentLike(userRepository.findByUsername("superman").get(),
            commentRepository.findById(13L).get()));


      }
    };
  }

}
