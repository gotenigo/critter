package com.udacity.jdnd.course3.critter.util;


import org.springframework.beans.BeanUtils;


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
