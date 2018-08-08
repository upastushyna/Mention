package org.danit.mention.controller;

import org.danit.mention.dto.LoginDetailsRq;
import org.danit.mention.dto.LoginDetailsRs;
import org.danit.mention.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  static Logger log = LoggerFactory.getLogger(LoginController.class);

  private AuthService authService;

  @Autowired
  public LoginController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping
  @CrossOrigin
  public LoginDetailsRs login(@RequestBody LoginDetailsRq loginDetailsRq) {
    log.info("__" + loginDetailsRq.toString());
    return new LoginDetailsRs(authService.login(loginDetailsRq));
  }


}
