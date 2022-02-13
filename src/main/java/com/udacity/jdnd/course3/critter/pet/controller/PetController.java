package com.udacity.jdnd.course3.critter.pet.controller;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.pet.domain.PetDTO;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.domain.Customer;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.udacity.jdnd.course3.critter.util.util.toPet;
import static com.udacity.jdnd.course3.critter.util.util.toPetDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    UserService userService;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        Pet pet = toPet(petDTO); // convert DTO to Pet
        pet= petService.savePet(pet); // save into Database

        return toPetDTO(pet);

    }



    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        Pet pet = petService.getPet(petId);
        PetDTO petDTO = toPetDTO(pet);

        return petDTO;
    }



    @GetMapping
    public List<PetDTO> getPets(){

        return petService.getPets()
                .stream()
                .map(x->toPetDTO(x))
                .collect(Collectors.toList());
    }



    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return petService.getPetsByOwner(ownerId)
                .stream()
                .map(x->toPetDTO(x))
                .collect(Collectors.toList());
    }



}
