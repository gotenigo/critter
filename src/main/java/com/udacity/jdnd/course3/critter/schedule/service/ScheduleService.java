package com.udacity.jdnd.course3.critter.schedule.service;


import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.schedule.domain.Schedule;
import com.udacity.jdnd.course3.critter.schedule.domain.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;


    public List<Schedule> createSchedule(List<Schedule> schedule) {
        return scheduleRepository.saveAll(schedule);
    }



    public List<Schedule> getAllSchedules() {

        return scheduleRepository.findAll();
    }



    public List<Schedule> getScheduleForPet(long petId) {

        return scheduleRepository.findScheduleByPet_Id(petId);
    }




    public List<Schedule> getScheduleForEmployee(long employeeId) {

        return scheduleRepository.findScheduleByByEmployee(employeeId);
    }



    public List<Schedule> getScheduleForCustomer(long customerId) {

        return scheduleRepository.getScheduleForCustomer(customerId);
    }



}
