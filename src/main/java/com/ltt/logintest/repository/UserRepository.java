package com.ltt.logintest.repository;


import org.springframework.data.repository.CrudRepository;

import com.ltt.logintest.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}
