package com.innotechnum.springdemo.repository;

import com.innotechnum.springdemo.entities.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepo extends CrudRepository<Model, Long> {
}
