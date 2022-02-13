package com.udacity.jdnd.course3.critter.user.controller;

import com.udacity.jdnd.course3.critter.user.domain.*;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.udacity.jdnd.course3.critter.util.util.*;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    //CUSTOMER

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        //Customer customer = copyProperties(customerDTO,new Customer());
        Customer customer = toCustomer(customerDTO);
                customer =  userService.saveCustomer(customer);
        return toCustomerDTO(customer);

    }


    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        List<Customer> customerList=userService.getAllCustomers();

        //System.out.println("====>userService.getAllCustomers="+customerList);

        List<CustomerDTO> customerDTOList= customerList.stream()
                .map(x -> toCustomerDTO(x))
                .collect(Collectors.toList());

        return customerDTOList;
    }





    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        Customer customer= userService.getOwnerByPet(petId);

        return toCustomerDTO(customer);
    }






// EMPLOYEE

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = toEmployee(employeeDTO);
        employee =  userService.saveEmployee(employee);

        return toEmployeeDTO(employee);
    }



    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        Employee employee=userService.getEmployee(employeeId);

        return copyProperties(employee,new EmployeeDTO());
    }






    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

        userService.setAvailability( daysAvailable,  employeeId);
    }





    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {

        DayOfWeek dayOfWeek= employeeDTO.getDate().getDayOfWeek();

        Set<Employee> employeeSet= userService.findEmployeesForService(employeeDTO.getSkills() ,  dayOfWeek );


        List<EmployeeDTO> employeeDTOList= employeeSet.stream()
                .map(x -> toEmployeeDTO(x))
                .collect(Collectors.toList());

        return employeeDTOList;
    }










}
