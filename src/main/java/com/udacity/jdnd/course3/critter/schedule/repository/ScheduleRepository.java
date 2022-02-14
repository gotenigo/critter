package com.udacity.jdnd.course3.critter.schedule.repository;



import com.udacity.jdnd.course3.critter.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;



@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    @Query("SELECT sc FROM Schedule sc WHERE sc.id.date IN (select sc2.id.date from Schedule sc2 where sc2.pet.id=:petId)")
    List<Schedule> findScheduleByPet_Id(long petId);



    @Query("SELECT sc FROM Schedule sc WHERE sc.id.date IN (select sc2.id.date from Schedule sc2 where sc2.employee.id=:employeeId)")
    List<Schedule> findScheduleByEmployee_Id(long employeeId);


    @Query("SELECT sc FROM Schedule sc WHERE sc.id.date IN (select sc2.id.date from Schedule sc2 where sc2.pet.owner.id=:customerId)")
    List<Schedule> getScheduleForCustomer(long customerId);




}
