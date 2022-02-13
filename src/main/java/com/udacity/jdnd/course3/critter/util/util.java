package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.pet.domain.Pet;
import com.udacity.jdnd.course3.critter.pet.domain.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.domain.Schedule;
import com.udacity.jdnd.course3.critter.schedule.domain.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.domain.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class util {



    /**
     *
     * Bounded Type Parameters :: used to Convert any Basic Object to DTO
     *
     * @param source
     * @param target
     * @param <T>
     * @param <U>
     * @return type U
     */
    public static  <T,U> U copyProperties(T source, U target){
        BeanUtils.copyProperties(source, target);

        return target;
    }































}
