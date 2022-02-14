package com.udacity.jdnd.course3.critter.pet.service;


import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class PetService {


    @Autowired
    PetRepository petRepository;



    public Pet savePet(Pet pet){

        return petRepository.save(pet); // Hibernate will not update Customer/Owner where the attribute are null. If attribute are new, then data will be cas cascade

    }





    public Pet getPet(long petId) {
        Optional<Pet> employee=petRepository.findById(petId);
        return employee.orElse(new Pet());
    }



    public List<Pet> getPets(){

        return petRepository.findAll();
    }


    public List<Pet> getPetsByOwner(long customerId) {

        return petRepository.findByOwner_Id(customerId);
    }





}
