package com.ltt.logintest.repository;


import org.springframework.data.repository.CrudRepository;

import com.ltt.logintest.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
