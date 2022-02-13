package com.udacity.jdnd.course3.critter.schedule.controller;


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















    //SCHEDULE

    //For output
    /**************
     *  For Output
     *
     *      Convert Data from List<Schedule>    TO    ScheduleDTO
     *
     * @param scheduleList List<Schedule>
     * @return ScheduleDTO
     ******************************/
    private static ScheduleDTO listToScheduleDTO(List<Schedule> scheduleList){

        Set<Schedule> scheduleSet = new LinkedHashSet(scheduleList);
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        Optional<Schedule> scheduleOptional = scheduleSet.stream().findFirst();

        if (scheduleSet==null || !scheduleOptional.isPresent() ){
            return scheduleDTO;
        }

        Schedule schedule = scheduleOptional.get();
        //set Date for the output ScheduleDTO
        scheduleDTO.setDate(schedule.getDate());

        //Set employee Activities for the output ScheduleDTO
        scheduleDTO.setActivities(schedule.getActivities());


        // Set the employeeIds for the output ScheduleDTO
        Set<Long> employeeIds=new LinkedHashSet<>();

        // Set the petIds for the output ScheduleDTO
        Set<Long> petIds=new LinkedHashSet<>();

        for (Schedule e : scheduleSet){

            employeeIds.add(e.getEmployee().getId());
            petIds.add(e.getPet().getId());
        }

        scheduleDTO.setEmployeeIds(new ArrayList<>(employeeIds));
        scheduleDTO.setPetIds(new ArrayList<>(petIds));

        return scheduleDTO;

    }



    //For input
    /**************
     *  For Input
     *
     *      Convert Data from ScheduleDTO  TO   List<Schedule>
     *
     * @param scheduleDTO
     * @return
     ******************************/
    private static List<Schedule> DtoToScheduleList(ScheduleDTO scheduleDTO){

        LocalDate date = scheduleDTO.getDate();
        Set<EmployeeSkill> activities=scheduleDTO.getActivities();

        List<Schedule> scheduleList=new ArrayList<>();
        Pet pet;
        Employee employee;

        for (Long petId : scheduleDTO.getPetIds()) {   // Convert Pet + Employee to List as per the DTO requirement

            pet = new Pet(petId);
            for (Long vEmployeeId : scheduleDTO.getEmployeeIds()) {
                employee = new Employee((vEmployeeId));

                Schedule schedule = new Schedule(date, activities, pet, employee);
                schedule.setEmployee(employee);
                schedule.setPet(pet);
                scheduleList.add(schedule);
            }
        }

        return scheduleList;
    }




    /**********
     * For Query
     *
     *   Convert Data from List<Schedule>   TO   List<ScheduleDTO>
     *
     * @param scheduleList List<Schedule>
     * @return  List<ScheduleDTO>
     *
     ***********************************/
    private static List<ScheduleDTO> toScheduleDTOList(List<Schedule> scheduleList){

        if (scheduleList.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        List<ScheduleDTO> scheduleDTOList= new ArrayList<>();

        Map<LocalDate,List<Schedule>> scheduleListGroupedByDate = scheduleList
                .stream()
                .collect(Collectors.groupingBy(e->e.getDate()));

        scheduleListGroupedByDate.entrySet()
                .stream()
                .forEach( x->{
                    ScheduleDTO dto = listToScheduleDTO(x.getValue());
                    scheduleDTOList.add(dto);
                });

        return scheduleDTOList;
    }








}
