package com.udacity.jdnd.course3.critter.schedule.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
public class SchedulePK implements Serializable {


    //employee
    @Column(name = "employee_id")
    private Long  employeeId;

    //pet
    @Column(name = "pet_id")
    private Long  petId;

    @Column(name = "localDate")
    private LocalDate date;




    public SchedulePK(){}

    public SchedulePK(LocalDate date) {
        this.date = date;
    }



    //Getters and Setters
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
        if (!(o instanceof SchedulePK)) return false;

        SchedulePK that = (SchedulePK) o;

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
