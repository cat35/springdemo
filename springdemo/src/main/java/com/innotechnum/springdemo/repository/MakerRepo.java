package com.innotechnum.springdemo.repository;

import com.innotechnum.springdemo.entities.Maker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakerRepo extends CrudRepository<Maker, Long> {
}
