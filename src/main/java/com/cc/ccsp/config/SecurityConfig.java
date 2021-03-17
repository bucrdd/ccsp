package com.cc.ccsp.config;

import com.cc.ccsp.security.jwt.JwtSecurityConfigure;
import com.cc.ccsp.security.jwt.JwtTokenProvider;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private UserDetailsService userDetailsService;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private JwtTokenProvider jwtTokenProvider;

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login").permitAll()  // login
        .antMatchers("/register").permitAll() // register
        .antMatchers("/doc.html","/webjars/**", "/swagger-resources/**", "/v2/api-docs/**").permitAll() // swagger
        .anyRequest().authenticated()
        .and()
        .apply(new JwtSecurityConfigure(jwtTokenProvider))
    ;
  }

}
