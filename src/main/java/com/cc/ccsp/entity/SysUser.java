package com.cc.ccsp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@Table(name = "sys_user")
@Entity
@ApiModel(value = "系统用户")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(length = 11)
  private Long id;

  @ApiModelProperty(value = "密码")
  private String password;

  @ApiModelProperty(value = "用户名")
  private String username;

  @ApiModelProperty(value = "用户角色")
  @ManyToMany(targetEntity = SysRole.class, fetch = FetchType.EAGER)
  @JoinTable(name = "sys_user_role",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
  )
  private Set<SysRole> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (Objects.isNull(roles)) {
      return Collections.emptyList();
    }
    return roles.stream().map(SysRole::getSymbol).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
