package com.mention.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

  @Autowired
  SmartUserDetailsService userDetailsService;

  @Autowired
  PasswordEncoder specificEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    /*
        auth
            .inMemoryAuthentication()
            .withUser("u1").password(specificEncoder.encode("p1")).roles("USER");
    */
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(specificEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .headers()
        .frameOptions()
        .disable()
        .and()

      .csrf().disable()

        .authorizeRequests()
        .antMatchers("/xu*", "/root").permitAll()
        .antMatchers("/xs*").authenticated()
        /*
                .antMatchers("/login/**").anonymous() // or .permitAll()
                .antMatchers("/reg*").permitAll()
                .antMatchers("/ul/**").permitAll()
                .antMatchers("/l/**","/").authenticated()
        */
        .and()

        .formLogin()
          .loginPage("/login")
          //.loginProcessingUrl("/login_perform")
          .defaultSuccessUrl("/")
          //.failureUrl("/login?error=true")
          .and()

        // logout section
        .logout()
          .logoutUrl("/logout") // this will triggered on "/logout POST"
          //.logoutSuccessUrl("/logouts")
          //.deleteCookies("JSESSIONID")
          .permitAll()
          .and()
    ;
  }

}