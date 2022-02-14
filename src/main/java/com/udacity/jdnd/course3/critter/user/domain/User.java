package com.udacity.jdnd.course3.critter.user.domain;


import View.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Nationalized;


import javax.persistence.*;
import javax.validation.constraints.Size;

/*@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)*/

//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "USER_TYPE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class User {


    @Id
    @GeneratedValue
    private Long id;


    //@JsonView(Views.Public.class)
    //@Nationalized // should use @Nationalized instead of @Type=nstring
    @Size(max=500)
    private String name;


    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
