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
//@Table(name = "SCHEDULE", uniqueConstraints={@UniqueConstraint(columnNames = {"localDate" , "employee_id", "pet_id"})})
@Table(name = "SCHEDULE")
public class Schedule  {


    @EmbeddedId
    private SchedulePK2 id;


    //employee
    //@NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "employee_id")
    @MapsId("employeeId")
    private Employee employee;


    //pet
    //@NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    @MapsId("petId")
    private Pet pet;



    //skills
    @Column(name = "activity")
    //@NotNull
    @ElementCollection(targetClass = EmployeeSkill.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;


    public Schedule(Employee employee, LocalDate date, Set<EmployeeSkill> activities) {
        this.employee = employee;
        this.id= new SchedulePK2(date);
        this.activities = activities;
    }


    public Schedule(Pet pet, LocalDate date, Set<EmployeeSkill> activities) {
        this.pet = pet;
        this.id= new SchedulePK2(date);
        this.activities = activities;

    }


    public Schedule(){
        this.id = new SchedulePK2();
    }



    public Schedule(LocalDate date, Set<EmployeeSkill> activities, Pet pet, Employee employee) {
        System.out.println("=======>Trying to create a schedulewith date ="+date);
        this.id= new SchedulePK2(date);
        this.activities=activities;
        this.pet=pet;
        this.employee=employee;
    }




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


    public SchedulePK2 getId() {
        return id;
    }

    public void setId(SchedulePK2 id) {
        this.id = id;
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
                ", date=" + this.id.getDate() +
                ", activities=" + activities +
                '}';
    }



}
