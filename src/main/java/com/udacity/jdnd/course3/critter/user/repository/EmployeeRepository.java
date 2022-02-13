package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Set<Employee> findEmployeeBySkillsInAndDaysAvailable(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);



}
