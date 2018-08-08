package org.danit.mention.service;

import org.danit.mention.dto.LoginDetailsRq;

public interface AuthService {

  boolean login(LoginDetailsRq loginDetailsRq);
}
