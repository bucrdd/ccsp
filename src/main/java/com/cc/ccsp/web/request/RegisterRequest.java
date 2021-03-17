package com.cc.ccsp.web.request;

import com.cc.ccsp.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@ApiModel(value = "注册用户信息")
public class RegisterRequest {

  @ApiModelProperty(value = "用户名")
  private String username;

  @ApiModelProperty(value = "密码")
  private String password;

  public SysUser toUser(PasswordEncoder passwordEncoder) {
    return SysUser.builder()
        .username(username)
        .password(passwordEncoder.encode(password))
        .build();
  }
}
