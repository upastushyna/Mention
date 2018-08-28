package com.mention.service;

/*@Service*/
public class AuthServiceImpl implements AuthService {

  /*private UserRepository userRepository;
  static Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

  @Autowired
  public AuthServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean login(LoginDetailsRq loginDetailsRq) {
    Optional<User> user = userRepository.findByUsername(loginDetailsRq.getUsername());
    log.info("__" + user.toString());
    if (user.isPresent()) {
      return user.get().getPassword().equals(loginDetailsRq.getPassword());
    } else {
      return false;
    }
  }*/
}
