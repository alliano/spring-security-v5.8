package com.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.SearchDto;
import com.spring.dto.UserDto;
import com.spring.dto.responseData.ResponseData;
import com.spring.model.entitys.User;
import com.spring.model.entitys.role.Role;
import com.spring.service.UserService;

import eye2web.modelmapper.ModelMapper;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   private ModelMapper modelMapper;

   @PostMapping(path = "/register")
   @CrossOrigin(origins = "http://localhost:8081")
   public ResponseEntity<ResponseData<User>> register(@RequestBody @Valid UserDto user,Errors errors){
      ResponseData<User> responseHttp = new ResponseData<User>();
     if(errors.hasErrors()){
        for (ObjectError error : errors.getAllErrors()) {
           responseHttp.getMessage().add(error.getDefaultMessage());
        }
        responseHttp.setStatus(false);
        responseHttp.setPayLoad(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHttp);
     }else{
        User userMapping = modelMapper.map(user, User.class);
        responseHttp.setStatus(true);
        responseHttp.getMessage().add("succes");
        if (user.getRole().equalsIgnoreCase("ADMIN")) {
           userMapping.setRole(Role.ADMIN);
        } else if(user.getRole().equalsIgnoreCase("USER") || 
        !user.getRole().equalsIgnoreCase("USER") || 
        !user.getRole().equalsIgnoreCase("ADMIN")
        || user.getRole().equalsIgnoreCase(null)){
           userMapping.setRole(Role.USER);
        }
        responseHttp.setPayLoad(userService.register(userMapping));
        return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
     }
   }

   @PostMapping(path = "/email")
   public User findByEmail(@RequestBody SearchDto search){
      System.out.println(search.getEmail());
      return userService.findByemail(search.getEmail());
   }
}
