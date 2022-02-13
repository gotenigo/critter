package com.udacity.jdnd.course3.critter.schedule.repository;


import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.schedule.domain.Schedule;
import com.udacity.jdnd.course3.critter.schedule.domain.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    List<Schedule> findScheduleByPet_Id(long petId);

    //@Query("SELECT sc.employee,sc.pet,sc.date,sc.activities FROM Schedule sc WHERE sc.employee.id = :employeeId GROUP BY sc.date")
     List<Schedule> findScheduleByEmployee_Id(long employeeId);


    @Query("SELECT sc FROM Schedule sc WHERE sc.pet.owner.id = :customerId")
    List<Schedule> getScheduleForCustomer(long customerId);





}
