package com.userfront.domain.security.dao;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.security.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
