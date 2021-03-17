package com.cc.ccsp.web.controller;

import com.cc.ccsp.entity.SysUser;
import com.cc.ccsp.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@Api(tags = "用户管理")
public class UserController {

  private UserRepository users;

  @Autowired
  public UserController(UserRepository users) {
    this.users = users;
  }

  @GetMapping
  @ApiOperation(value = "用户列表")
  public List<SysUser> allUsers() {
    return (List<SysUser>) users.findAll();
  }
}
