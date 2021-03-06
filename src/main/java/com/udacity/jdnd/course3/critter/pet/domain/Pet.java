package com.udacity.jdnd.course3.critter.pet.domain;

import com.udacity.jdnd.course3.critter.schedule.domain.Schedule;
import com.udacity.jdnd.course3.critter.user.domain.Customer;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;



@Entity
@Table(name = "PET")
public class Pet {

    @Id
    @GeneratedValue
    private long id;

    private PetType type;

    @Size(max=500)
    private String name;

    private LocalDate birthDate;

    @Size(max=10000)
    private String notes;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;


    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Schedule> scheduleList;






    //Construct
    public Pet(long id){
        this.id=id;
    }

    public  Pet(){}






    //getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }


    public Set<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(Set<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }


    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                ", owner=" + owner +
                //", scheduleList=" + scheduleList.stream().map(x->x.getId()).collect(Collectors.toList()) +
                '}';
    }
}
