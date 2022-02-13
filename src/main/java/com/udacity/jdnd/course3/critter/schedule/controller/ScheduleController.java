package com.udacity.jdnd.course3.critter.schedule.controller;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.schedule.domain.Schedule;
import com.udacity.jdnd.course3.critter.schedule.domain.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


import static com.udacity.jdnd.course3.critter.util.util.*;

//import static com.udacity.jdnd.course3.critter.util.util.toSchedule;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;



    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        List<Schedule> scheduleList =DtoToScheduleList(scheduleDTO);

        scheduleList =  scheduleService.createSchedule(scheduleList);

        return listToScheduleDTO(scheduleList);
    }








    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        List<Schedule> scheduleList = scheduleService.getAllSchedules();

        return toScheduleDTOList(scheduleList);
    }





    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        List<Schedule> scheduleList = scheduleService.getScheduleForPet(  petId) ;

        return toScheduleDTOList(scheduleList);
    }





    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {

        List<Schedule> scheduleList = scheduleService.getScheduleForEmployee( employeeId);

        return toScheduleDTOList(scheduleList);
    }




    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {

        List<Schedule> scheduleList = scheduleService.getScheduleForCustomer( customerId);

        return toScheduleDTOList(scheduleList);
    }



}
