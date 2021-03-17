package com.cc.ccsp.repository;

import com.cc.ccsp.entity.SysUser;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<SysUser, Long> {

  Optional<SysUser> findByUsername(String username);

  boolean existsByUsername(String username);
}
