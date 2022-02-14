package com.udacity.jdnd.course3.critter.user.service;


import com.udacity.jdnd.course3.critter.user.domain.*;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;


@Transactional
@Service
public class UserService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    //@Autowired
    //UserRepositoryEntityManager userRepositoryEntityManager; // same solution but with EntityManager




    //CUSTOMER
    public Customer saveCustomer( Customer customer){

         return customerRepository.save(customer);

    }


    public List<Customer> getAllCustomers(){

        return customerRepository.findAll();

    }



    public Customer getOwnerByPet(long petId) {

        return customerRepository.getOwnerByPet(petId);
    }






    // EMPLOYEE
    public Employee saveEmployee( Employee employee){

        return employeeRepository.save(employee);
    }



    public Employee getEmployee(long employeeId) {

        return employeeRepository.findById(employeeId).orElse(new Employee());
    }




    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isPresent()){
            employee.get().setDaysAvailable(daysAvailable);
            employeeRepository.save(employee.get());
        }

    }




    public Set<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {

         Set<Employee> employeeSet =  employeeRepository.findEmployeeBySkillsInAndDaysAvailable(skills,dayOfWeek);

         employeeSet= employeeSet.stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toSet());

        return employeeSet;
    }




}
