package com.udacity.jdnd.course3.critter.user.domain;
import com.udacity.jdnd.course3.critter.pet.domain.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name ="CUSTOMER")
//@DiscriminatorValue("CUSTOMER")
public class Customer extends User{

    private String name;
    private String phoneNumber;
    private String notes;


    //pet - Yes makes sense
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> petList;



    public Customer(){
        this.petList=new ArrayList<>();
    }



    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", petList=" + petList +
                '}';
    }
}
