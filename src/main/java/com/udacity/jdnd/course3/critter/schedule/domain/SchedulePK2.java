package com.udacity.jdnd.course3.critter.schedule.domain;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.EmployeeSkill;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
public class SchedulePK2 implements Serializable {


    //employee
    @Column(name = "employee_id")
    private Long  employeeId;

    //pet
    @Column(name = "pet_id")
    private Long  petId;

    @Column(name = "localDate")
    private LocalDate date;


    public SchedulePK2(){}

    public SchedulePK2(LocalDate date) {
        this.date = date;
    }

    public SchedulePK2(Long employeeId, Long petId) {
        this.employeeId = employeeId;
        this.petId = petId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchedulePK2)) return false;

        SchedulePK2 that = (SchedulePK2) o;

        if (!getEmployeeId().equals(that.getEmployeeId())) return false;
        if (!getPetId().equals(that.getPetId())) return false;
        return getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        int result = getEmployeeId().hashCode();
        result = 31 * result + getPetId().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }
}
