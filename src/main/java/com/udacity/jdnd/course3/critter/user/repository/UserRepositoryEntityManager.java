package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.domain.Customer;
import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Transactional
//@Transactional annotation either at the class or method level to ensure all your methods execute inside a transaction.
@Repository
//DAO implementation should still use the @Repository annotation, because it is a Spring Component that handles database interactions.
public class UserRepositoryEntityManager {


    @PersistenceContext
    EntityManager entityManager;



    // EMPLOYEE
    public void saveEmployee( Employee employee){

         entityManager.persist(employee);

    }



    //@Transactional
    public List<Customer> getAllCustomers(){


     CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);    // FROM customer
        criteria.select(root);


        return entityManager.createQuery(criteria).getResultList();


    }



    public Employee getEmployee(long employeeId) {

        //Optional<Employee> employee=employeeRepository.findById(employeeId);
        //return employee.orElse(new Employee());

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);   // FROM employee
        criteria.select(root).where(cb.equal(root.get("id"),employeeId)); // WHERE id={employeeId}

        return entityManager.createQuery(criteria).getSingleResult();
    }




    // CUSTOMER
    public Customer saveCustomer( Customer customer){

        customer.setId(null); // need to avoid detached Entity issue

        customer.getPetList().forEach(pet->pet.setOwner(customer));

        entityManager.persist(customer);

        return getCustomer(customer.getId());

    }


    public Customer getCustomer(long customerId) {

        //Optional<Employee> employee=employeeRepository.findById(employeeId);
        //return employee.orElse(new Employee());

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);   // FROM employee
        criteria.select(root).where(cb.equal(root.get("id"),customerId)); // WHERE id={employeeId}

        return entityManager.createQuery(criteria).getSingleResult();
    }





}
