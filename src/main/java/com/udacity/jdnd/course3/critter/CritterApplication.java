package com.udacity.jdnd.course3.critter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Launches the Spring application. Unmodified from starter code.
 *
 * This Spring Boot project will allow users to create pets, owners, and employees,
 * and then schedule events for employees to provide services for pets.
 *
 * ======The project should  support the following workflow:=====
 *
 * 1) Create a new customer.
 * 2) Create a pet for that customer.
 * 3) Create an employee.
 * 4) Update the employee’s schedule.
 * 5) Find out which employees with the right skills are available on a given date.
 * 6) Schedule one or more employees to do a set of activities with one or more pets. => !!!  Employee Vs pets : many to Many !!!
 * 7) Look up currently scheduled events for an employee, a pet, or a customer.
 *
 *
 */
@SpringBootApplication
public class CritterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CritterApplication.class, args);
	}

}
