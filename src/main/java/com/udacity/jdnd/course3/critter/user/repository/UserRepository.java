package com.udacity.jdnd.course3.critter.user.repository;


import com.udacity.jdnd.course3.critter.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
