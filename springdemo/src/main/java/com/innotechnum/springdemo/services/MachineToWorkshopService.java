package com.innotechnum.springdemo.services;

import com.innotechnum.springdemo.entities.MachineToWorkshop;
import com.innotechnum.springdemo.repository.MachineToWorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class MachineToWorkshopService {

    private final MachineToWorkshopRepo machineToWorkshopRepo;

    @Autowired
    public MachineToWorkshopService(MachineToWorkshopRepo machineToWorkshopRepo) {
        this.machineToWorkshopRepo = machineToWorkshopRepo;
    }

    @Transactional
    public MachineToWorkshop transfer(LocalDate date, Long idMachine, Long idWorkshop){
            MachineToWorkshop machineToWorkshop = machineToWorkshopRepo.findFirstByIdMachineOrderByDateInDesc(idMachine);
            if(null!=machineToWorkshop) {
                machineToWorkshop.setDateOut(date);
                machineToWorkshopRepo.save(machineToWorkshop);
            }
            MachineToWorkshop newMachineToWorkshop1 = new MachineToWorkshop();
        newMachineToWorkshop1.setDateIn(date.plusDays(1));
        newMachineToWorkshop1.setIdWorkshop(idWorkshop);
        return machineToWorkshopRepo.save(newMachineToWorkshop1);

    }
}
