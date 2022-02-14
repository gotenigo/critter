package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.DayOfWeek;
import java.util.Set;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Set<Employee> findEmployeeBySkillsInAndDaysAvailable(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);



}
