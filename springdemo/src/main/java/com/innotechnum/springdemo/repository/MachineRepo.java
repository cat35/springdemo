package com.innotechnum.springdemo.repository;

import com.innotechnum.springdemo.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MachineRepo extends CrudRepository<Machine, Long> {

    List<Machine> findByModelId(Long modelId);
    Optional<Machine> findByIdAndModelId(Long id, Long modelId);

}
