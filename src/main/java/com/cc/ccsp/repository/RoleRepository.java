package com.cc.ccsp.repository;

import com.cc.ccsp.entity.SysRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<SysRole, Long> {

  boolean existsBySymbol(String symbol);
}
