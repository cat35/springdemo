package com.innotechnum.springdemo.services;

import com.innotechnum.springdemo.entities.MachineToWorkshop;
import com.innotechnum.springdemo.repository.MachineToWorkshopRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Slf4j
public class MachineToWorkshopService {

    private final MachineToWorkshopRepo machineToWorkshopRepo;

    @Autowired
    public MachineToWorkshopService(MachineToWorkshopRepo machineToWorkshopRepo) {
        this.machineToWorkshopRepo = machineToWorkshopRepo;
    }

    @Transactional
    public MachineToWorkshop transfer(LocalDate date, Long idMachine, Long idWorkshop){
            MachineToWorkshop machineToWorkshop = machineToWorkshopRepo.findFirstByIdMachineOrderByDateInDesc(idMachine);
            log.info ("======={}", machineToWorkshop);
            if(null!=machineToWorkshop) {
                machineToWorkshop.setDateOut(date);
                machineToWorkshopRepo.save(machineToWorkshop);
            }
            MachineToWorkshop newMachineToWorkshop1 = new MachineToWorkshop();
        newMachineToWorkshop1.setDateIn(date.plusDays(1));
        newMachineToWorkshop1.setIdWorkshop(idWorkshop);
        newMachineToWorkshop1.setIdMachine(idMachine);
        return machineToWorkshopRepo.save(newMachineToWorkshop1);

    }
}
