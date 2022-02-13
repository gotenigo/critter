package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.user.domain.Customer;
import com.udacity.jdnd.course3.critter.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {



    @Query("SELECT cus FROM Customer cus WHERE cus.id IN (SELECT pet.owner.id FROM Pet pet WHERE pet.id= :petId)")
    Customer getOwnerByPet(long petId);

}



