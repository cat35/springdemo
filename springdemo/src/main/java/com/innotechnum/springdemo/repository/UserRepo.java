package com.innotechnum.springdemo.repository;

import com.innotechnum.springdemo.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
