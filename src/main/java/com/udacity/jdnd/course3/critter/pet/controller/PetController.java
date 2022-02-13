package com.udacity.jdnd.course3.critter.pet.controller;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.pet.domain.PetDTO;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.domain.Customer;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static com.udacity.jdnd.course3.critter.util.util.copyProperties;


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
        System.out.println("pet="+pet);

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







    /***
     *
     * @param pet
     * @return
     */
    private static PetDTO toPetDTO(Pet pet){

        if (pet==null  || pet.getOwner()==null){
            return new PetDTO() ;
        }

        PetDTO petdto = copyProperties(pet, new PetDTO());
        petdto.setOwnerId(pet.getOwner().getId());

        return petdto;
    }





    /***
     *
     * @param petDTO
     * @return
     */
    private static  Pet toPet(PetDTO petDTO){

        if (petDTO==null  ){
            return new Pet() ;
        }

        Customer customer = new Customer();
        customer.setId(petDTO.getOwnerId()); //!! Set the Pet ownership as they have ONE-TO-MANY relationship

        Pet pet = copyProperties(petDTO, new Pet());
        pet.setOwner(customer);

        return pet;
    }







}
