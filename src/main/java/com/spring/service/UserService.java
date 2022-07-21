package com.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.model.entitys.User;
import com.spring.model.repositorys.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService{

   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("email dengan \s gaada",email)));
   }

   public User register(User user){
      Optional<User> result = userRepository.findByEmail(user.getEmail());
      if(result.isPresent()){
         throw new RuntimeException(String.format("user with \s already exist", user.getEmail()));
      }else if(user.getEmail().isEmpty() || user.getName().isEmpty() || user.getPassword().isEmpty()){
         throw new RuntimeException("please entering all fields by correctly!");
      }else{
         try {
            String passEncoded = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(passEncoded);
            return userRepository.save(user);
         } catch(DataIntegrityViolationException e){
            log.warn("error DataIntegrityViolationException", e.getMessage());
            return null;
         }catch (Exception e) {
            log.info(String.format("ada error saat registrasikan user di service", e.getMessage()));
            return null;
         }
      }
   }

   // public User register(User user){
   //    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
   //    return userRepository.save(user);
   // }

   public User findByemail(String email){
      return userRepository.findByEmail(email).orElseThrow(() -> 
      new UsernameNotFoundException("user not found"));
   }
 

}
