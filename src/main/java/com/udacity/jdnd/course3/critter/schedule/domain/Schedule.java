package com.udacity.jdnd.course3.critter.schedule.domain;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.EmployeeSkill;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "SCHEDULE")
public class Schedule  {


    @EmbeddedId
    private SchedulePK id;


    //Employee
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "employee_id")
    @MapsId("employeeId")
    private Employee employee;


    //Pet
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    @MapsId("petId")
    private Pet pet;



    //Skills
    @Column(name = "activity")
    @NotNull
    @ElementCollection(targetClass = EmployeeSkill.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;




    public Schedule(){
        this.id = new SchedulePK();
    }


    public Schedule(LocalDate date, Set<EmployeeSkill> activities, Pet pet, Employee employee) {

        this.id= new SchedulePK(date);
        this.activities=activities;
        this.pet=pet;
        this.employee=employee;
    }



    //Getters and Setters
    public LocalDate getDate() {
        return this.id.getDate();
    }

    public void setDate(LocalDate date) {
        this.id.setDate(date);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }


    public SchedulePK getId() {
        return id;
    }

    public void setId(SchedulePK id) {
        this.id = id;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;

        Schedule schedule = (Schedule) o;

        return getId().equals(schedule.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }





    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", employee=" + employee.getId() +
                ", pet=" + pet.getId() +
                ", date=" + this.id.getDate() +
                ", activities=" + activities +
                '}';
    }






}
