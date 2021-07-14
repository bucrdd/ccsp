package com.cc.ccsp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sys_role")
@ApiModel(value = "系统角色")
public class SysRole extends AbstractAuditableEntity<SysUser, Long> {

  private static final long serialVersionUID = 6702119248550084222L;

  @ApiModelProperty(value = "角色名")
  private String name;

  @ApiModelProperty(value = "角色标识")
  private String symbol;

  @Column(nullable = false, columnDefinition = "bit default false")
  private Boolean enable;

}

