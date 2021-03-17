package com.cc.ccsp.web.controller;

import com.cc.ccsp.repository.UserRepository;
import com.cc.ccsp.security.jwt.JwtProperties;
import com.cc.ccsp.security.jwt.JwtTokenProvider;
import com.cc.ccsp.web.request.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
@Api(tags = "登录模块")
public class LoginController {

  private AuthenticationManager authenticationManager;

  private JwtTokenProvider jwtTokenProvider;

  private UserRepository users;

  @Autowired
  public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
      UserRepository users) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    this.users = users;
  }

  @PostMapping
  @ApiOperation(value = "用户登录")
  public Map<String, String> login(@RequestBody LoginRequest data) {
    log.debug("User login: " + data.getUsername());
    String username = data.getUsername();
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
      String token = jwtTokenProvider.createToken(
          users.findByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException("用户名[" + username + "]不存在"))
      );
      Map<String, String> model = new HashMap<>();
      model.put("username", username);
      model.put("token", token);
      return model;
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("用户名/密码错误！");
    }
  }

}
