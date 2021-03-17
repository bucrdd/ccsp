package com.cc.ccsp.web.controller;

import com.cc.ccsp.entity.SysRole;
import com.cc.ccsp.repository.RoleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
@Api(tags = "角色管理")
public class RoleController {

  private RoleRepository roles;

  @Autowired
  public RoleController(RoleRepository roles) {
    this.roles = roles;
  }

  @GetMapping
  @ApiOperation(value = "角色列表")
  public List<SysRole> allRoles() {
    return (List<SysRole>) roles.findAll();
  }

  @PostMapping
  @ApiOperation(value = "添加新角色")
  public SysRole addRole(@RequestBody SysRole role) {
    if (roles.existsBySymbol(role.getSymbol())) {
      //TODO: throw exception of duplicate role symbol

    }
    if (Objects.isNull(role.getVersion())) {
      role.setVersion(0L);
    }
    role.setVersion(role.getVersion() + 1);
    role = roles.save(role);
    return role;
  }
}
