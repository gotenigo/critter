package com.udacity.jdnd.course3.critter.schedule.domain;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.EmployeeSkill;

import javax.persistence.*;
import java.io.Serializable;


//@Embeddable
public class SchedulePK2 implements Serializable {


    //@Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "date_id")
    private Long dateId;

    //employee
    @Column(name = "employee_id")
    private Long  employeeId;

    //pet
    @Column(name = "pet_id")
    private Long  petId;


    public SchedulePK2(){}

    public SchedulePK2(Long date, Long employeeId, Long petId) {
        this.dateId = date;
        this.employeeId = employeeId;
        this.petId = petId;
    }

    public Long getDate() {
        return dateId;
    }

    public void setDate(Long date) {
        this.dateId = date;
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






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchedulePK2)) return false;

        SchedulePK2 that = (SchedulePK2) o;

        if (!getDate().equals(that.getDate())) return false;
        if (!getEmployeeId().equals(that.getEmployeeId())) return false;
        return getPetId().equals(that.getPetId());
    }

    @Override
    public int hashCode() {
        int result = getDate().hashCode();
        result = 31 * result + getEmployeeId().hashCode();
        result = 31 * result + getPetId().hashCode();
        return result;
    }



}
