package com.udacity.jdnd.course3.critter.schedule.domain;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.EmployeeSkill;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "SCHEDULE", uniqueConstraints={@UniqueConstraint(columnNames = {"localDate" , "employee_id", "pet_id"})})
public class Schedule  {


    @Id
    @GeneratedValue
    private Long id;


    //employee
    //@NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "employee_id")
    private Employee employee;


    //pet
    //@NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private Pet pet;



    //Date
    @Column(name = "localDate")
    private LocalDate date;



    //skills
    @Column(name = "activity")
    //@NotNull
    @ElementCollection(targetClass = EmployeeSkill.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;


    public Schedule(Employee employee, LocalDate date, Set<EmployeeSkill> activities) {
        this.employee = employee;
        this.date = date;
        this.activities = activities;
    }


    public Schedule(Pet pet, LocalDate date, Set<EmployeeSkill> activities) {
        this.pet = pet;
        this.date = date;
        this.activities = activities;
    }


    public Schedule(){
    }



    public Schedule(LocalDate date, Set<EmployeeSkill> activities, Pet pet, Employee employee) {
        this.date=date;
        this.activities=activities;
        this.pet=pet;
        this.employee=employee;
    }




    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;

        Schedule schedule = (Schedule) o;

        if (!getEmployee().equals(schedule.getEmployee())) return false;
        if (!getPet().equals(schedule.getPet())) return false;
        if (!getDate().equals(schedule.getDate())) return false;
        return getActivities().equals(schedule.getActivities());
    }

    @Override
    public int hashCode() {
        int result = getEmployee().hashCode();
        result = 31 * result + getPet().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getActivities().hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", employee=" + employee.getId() +
                ", pet=" + pet.getId() +
                ", date=" + date +
                ", activities=" + activities +
                '}';
    }



}
