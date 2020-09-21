package com.innotechnum.springdemo.repository;

import com.innotechnum.springdemo.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepo extends CrudRepository<Workshop, Long> {
}
