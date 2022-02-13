package com.udacity.jdnd.course3.critter.user.service;


import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.user.domain.*;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.repository.UserRepository;
import com.udacity.jdnd.course3.critter.user.repository.UserRepositoryEntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


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
