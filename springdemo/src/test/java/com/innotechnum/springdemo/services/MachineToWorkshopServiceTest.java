package com.innotechnum.springdemo.services;

import com.innotechnum.springdemo.entities.Machine;
import com.innotechnum.springdemo.entities.MachineToWorkshop;
import com.innotechnum.springdemo.entities.Workshop;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MachineToWorkshopServiceTest {

    MachineToWorkshopService machineToWorkshopService = Mockito.mock(MachineToWorkshopService.class);

    Machine machine = Mockito.mock(Machine.class);

    Workshop workshop = Mockito.mock(Workshop.class);

    MachineToWorkshop machineToWorkshop = Mockito.mock(MachineToWorkshop.class);

    @Test
    void transfer() {
        Mockito.when(machine.getId()).thenReturn(1L);
        Mockito.when(workshop.getId()).thenReturn(1L);
        Mockito.when(machineToWorkshop.getDateOut()).thenReturn(LocalDate.now());

        machineToWorkshopService.transfer(machineToWorkshop.getDateOut(), machine.getId(), workshop.getId());

        MachineToWorkshop machineToWorkshop1 = new MachineToWorkshop();
        machineToWorkshop1.setDateIn(machineToWorkshop.getDateOut().plusDays(1));

        assertTrue(machineToWorkshop.getDateOut().compareTo(machineToWorkshop1.getDateIn()) < 0);

    }
}