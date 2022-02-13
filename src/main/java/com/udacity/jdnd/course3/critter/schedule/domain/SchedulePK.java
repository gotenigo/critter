package com.udacity.jdnd.course3.critter.schedule.domain;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.user.domain.Employee;
import com.udacity.jdnd.course3.critter.user.domain.EmployeeSkill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.persistence.*;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


//@Embeddable
public class SchedulePK implements Serializable {

    // create your own serialVersionUID from your IDE if it asks
    private static final long serialVersionUID = 6202269445639364170L;


    @NotNull
    @Column(name = "date")
    private LocalDate date;

    //employee
    @NotNull
    @Column(name = "activity")
    @Enumerated(EnumType.STRING)
    private EmployeeSkill  activity;


    public SchedulePK(){}

    public SchedulePK(LocalDate date, EmployeeSkill activitiesId) {
        this.date = date;
        this.activity = activitiesId;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EmployeeSkill getActivity() {
        return activity;
    }

    public void setActivity(EmployeeSkill activity) {
        this.activity = activity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchedulePK)) return false;

        SchedulePK that = (SchedulePK) o;

        if (!getDate().equals(that.getDate())) return false;
        return getActivity().equals(that.getActivity());
    }

    @Override
    public int hashCode() {
        int result = getDate().hashCode();
        result = 31 * result + getActivity().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SchedulePK{" +
                "date=" + date +
                ", activity='" + activity + '\'' +
                '}';
    }
}
