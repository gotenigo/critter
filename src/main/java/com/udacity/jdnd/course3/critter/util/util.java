package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.pet.domain.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.domain.Schedule;
import com.udacity.jdnd.course3.critter.schedule.domain.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.domain.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.Date;

public class util {



    /**
     *
     * Bounded Type Parameters :: Convetr any Object to DTO
     *
     * @param source
     * @param target
     * @param <T>
     * @param <U>
     * @return type U
     */
    public static  <T,U> U copyProperties(T source, U target){
        BeanUtils.copyProperties(source, target);

        return target;
    }






    //  PET
    public static  PetDTO toPetDTO(Pet pet){

        PetDTO petdto = copyProperties(pet, new PetDTO());
        petdto.setOwnerId(pet.getOwner().getId());

        return petdto;
    }


    /***
     *
     * @param petDTO
     * @return
     */
    public static  Pet toPet(PetDTO petDTO){

        Customer customer = new Customer();
        customer.setId(petDTO.getOwnerId()); //!!!MUST KEEP !!!! you need to keep that as it specified by user in the POST  Method. Thats how you define the ownership

        Pet pet = copyProperties(petDTO, new Pet());
        pet.setOwner(customer);


        return pet;
    }






  // CUSTOMER
    public static  CustomerDTO toCustomerDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();

        if (customer==null || customer.getPetList()==null ){

            customerDTO.setPetIds(new ArrayList<>());
            return customerDTO;

        }

        List<Long> petIds = customer.getPetList()
                    .stream()
                    .map(x -> x.getId())
                    .collect(Collectors.toList());

        customerDTO = copyProperties(customer, new CustomerDTO());
        customerDTO.setPetIds(petIds);
        customerDTO.setId(customer.getId()); // // !! This is needed as DTO is report back after reading from Database

        return customerDTO;

    }






    public static  Customer toCustomer(CustomerDTO customerDTO){

        Customer customer = new Customer();

        if ( customerDTO==null || customerDTO.getPetIds()==null ){ // just for security

            customer.setPetList(new ArrayList<>());
            System.out.println("!!!! Something ODD with customerDTO="+customerDTO);
            return customer;
        }

        List<Pet> PetList = customerDTO.getPetIds()
                .stream()
                .map(x -> new Pet(x) )
                .collect(Collectors.toList());

        customer = copyProperties(customerDTO, new Customer());
        customer.setPetList(PetList);
        customer.setId(customerDTO.getId()); // ????????? should not be needed cause of copyProperties.
                                             //Also  we only support create a new object. So Spring create a new Id

        return customer;

    }








    // EMPLOYEE
    public static EmployeeDTO toEmployeeDTO(Employee employee){

        //System.out.println("======>employee="+employee);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        if (employee==null || employee.getSkills()==null){

            employeeDTO.setSkills(new LinkedHashSet<>());
            //employeeDTO.setDaysAvailable(new LinkedHashSet<>());
            return employeeDTO;

        }

        //System.out.println("UTIL===>employee="+employee);
        employeeDTO = copyProperties(employee, new EmployeeDTO());
        employeeDTO.setId(employee.getId()); // !! This is needed as DTO is report back after reading from Database


        //System.out.println("UTIL===>employeeDTO="+employeeDTO);
        return employeeDTO;

    }





    public static  Employee toEmployee(EmployeeDTO employeeDTO){

        Employee employee = new Employee();

        if ( employeeDTO==null || employeeDTO.getSkills()==null ){ // just for security

            employee.setDaysAvailable(new LinkedHashSet<>());
            employee.setSkills(new LinkedHashSet<>());
            System.out.println("!!!! Something ODD with employee="+employee);
            return employee;
        }

        employee = copyProperties(employeeDTO, new Employee());
        employee.setId(employeeDTO.getId()); // ?????? should not be needed cause of copyProperties
                                           //Also  we only support create a new object. So Spring create a new Id

        return employee;
    }




    public static  Date toDate(LocalDate localDate){

        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = (Date) Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        return date;
    }













    //SCHEDULE

    //For output
    public static ScheduleDTO listToScheduleDTO(List<Schedule> scheduleList){

        Set<Schedule> scheduleSet = new LinkedHashSet(scheduleList);

        System.out.println("[listToScheduleDTO] 111===>schedule="+scheduleList);
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        Optional<Schedule> scheduleOptional = scheduleSet.stream().findFirst();

        if (scheduleSet==null || !scheduleOptional.isPresent() ){
            return scheduleDTO;
        }

        Schedule schedule = scheduleOptional.get();
        //set Date
        scheduleDTO.setDate(schedule.getDate());

        //Set employee skills
        scheduleDTO.setActivities(schedule.getActivities());


        // employeeIds
        Set<Long> employeeIds=new LinkedHashSet<>();

        // petIds
        Set<Long> petIds=new LinkedHashSet<>();

        for (Schedule e : scheduleSet){
            //set employeeId + petId
            employeeIds.add(e.getEmployee().getId());
            petIds.add(e.getPet().getId());
            System.out.println("*****TEST******* ="+e.getEmployee().getScheduleList());
            System.out.println("*****TEST******* ="+e.getPet().getScheduleList());
        }

        scheduleDTO.setEmployeeIds(new ArrayList<>(employeeIds));
        scheduleDTO.setPetIds(new ArrayList<>(petIds));

        System.out.println("222===>scheduleDTO="+scheduleDTO);

        return scheduleDTO;

    }



    //For input
    public static List<Schedule> DtoToScheduleList(ScheduleDTO scheduleDTO){

        System.out.println("scheduleDTO="+scheduleDTO);


        LocalDate date = scheduleDTO.getDate();
        Set<EmployeeSkill> activities=scheduleDTO.getActivities();
        List<Schedule> outScheduleList= new ArrayList<>();

        List<Schedule> scheduleList=new ArrayList<>();
        Pet pet;
        Employee employee;

        for (Long petId : scheduleDTO.getPetIds()) {

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



    public static List<ScheduleDTO> toScheduleDTOList(List<Schedule> scheduleList){

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



/*
    public static ScheduleDTO toScheduleDTO(Schedule schedule){


        //System.out.println("111===>schedule="+schedule);
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        if (schedule==null ){
            return scheduleDTO;
        }

        //Set Id
        scheduleDTO.setId(schedule.getId());

        //set Date
        scheduleDTO.setDate(schedule.getDate());

        //Set employee skills
        scheduleDTO.setActivities(schedule.getActivities());

        //Set the employeeIds
        List<Long> employeeIds=new ArrayList<>();
        employeeIds.add(schedule.getEmployee().getId());
        scheduleDTO.setEmployeeIds(employeeIds);

        //Set the petIds
        List<Long> petIds=new ArrayList<>();
        petIds.add(schedule.getPet().getId());
        scheduleDTO.setPetIds(petIds);

        System.out.println("111===>scheduleDTO="+scheduleDTO);

        return scheduleDTO;

    }
*/







}
