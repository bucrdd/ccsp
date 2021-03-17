package com.cc.ccsp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "sys_role")
@Entity
@ApiModel(value = "系统角色")
public class SysRole extends AbstractAuditableEntity<SysUser, Long> {

  @ApiModelProperty(value = "角色名")
  private String name;

  @ApiModelProperty(value = "角色标识")
  private String symbol;
}

