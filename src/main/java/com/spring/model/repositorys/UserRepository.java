package com.spring.model.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.entitys.User;



public interface UserRepository extends JpaRepository<User,Long>{
   
   public Optional<User> findByEmail(String email);
}
