package com.cc.ccsp.web.controller;

import com.cc.ccsp.entity.SysUser;
import com.cc.ccsp.repository.UserRepository;
import com.cc.ccsp.web.request.RegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@Api(tags = "注册模块")
public class RegisterController {

  private UserRepository users;

  private PasswordEncoder passwordEncoder;

  @Autowired
  public RegisterController(UserRepository users, PasswordEncoder passwordEncoder) {
    this.users = users;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping
  @ApiOperation(value = "用户注册")
  public SysUser register(@RequestBody RegisterRequest data) {
    SysUser user = data.toUser(passwordEncoder);
    if (users.existsByUsername(data.getUsername())) {
      return new SysUser();
    }
    return users.save(user);
  }
}
