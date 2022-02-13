package com.udacity.jdnd.course3.critter.user.domain;

import com.udacity.jdnd.course3.critter.schedule.domain.Schedule;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name ="EMPLOYEE")
//@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {


    //skills
    @ElementCollection(targetClass = EmployeeSkill.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;


    // Employee works from Mon...Sun
    //availability : Mon, Tues, Wed, Thur, Fri, Sat, Sun
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;



    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Schedule> scheduleList;




    public Employee(){
        super();
        this.skills=new LinkedHashSet<>();
        this.daysAvailable=new LinkedHashSet<>();
    }


    public Employee(long id){
        super();
        this.setId(id);
        this.skills=new LinkedHashSet<>();
        this.daysAvailable=new LinkedHashSet<>();
    }






    //Getters and Setters
    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }


    public Set<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(Set<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }





    @Override
    public String toString() {
        return "Employee{" +
                "id=" + super.getId() +
                ",skills=" + skills +
                ", daysAvailable=" + daysAvailable +
                //", scheduleList=" + scheduleList.stream().map(x->x.getId()).collect(Collectors.toList()) +
                '}';
    }





}
