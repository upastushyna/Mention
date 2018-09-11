package com.mention.security;

import com.mention.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = getJwtFromRequest(request);

      if (tokenProvider.validateToken(jwt)) {
        Long userId = tokenProvider.getUserIdFromJwt(jwt);

        UserDetails userDetails = userDetailsService.loadUserById(userId);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception ex) {
      logger.error("Could not set user authentication in security context", ex);
    }

    filterChain.doFilter(request, response);

  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String Auth1 = Constants.AUTH_HEADER;
    String Auth2 = Constants.AUTH_TOKEN;
    String bearerToken = request.getHeader(Auth1);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.AUTH_BEARER)) {
      return bearerToken.substring(Constants.AUTH_BEARER.length(), bearerToken.length());
    } else {
      String accToken = request.getParameter(Auth2);
      if (accToken != null) {
        return accToken.substring(Constants.AUTH_BEARER.length(), accToken.length());
      } else {
        return null;
      }
    }
  }
}
