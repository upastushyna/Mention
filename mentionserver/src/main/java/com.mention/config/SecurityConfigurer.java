package com.mention.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()

        .authorizeRequests()
        .antMatchers("/*").permitAll()
/*
                .antMatchers("/login/**").anonymous() // or .permitAll()
                .antMatchers("/reg*").permitAll()
                .antMatchers("/ul/**").permitAll()
                .antMatchers("/l/**","/").authenticated()
*/
        .and()

    ;


  }
}
