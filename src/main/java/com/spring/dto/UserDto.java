package com.spring.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDto {
   
   @NotEmpty(message = "name is required")
   private String name;

   @NotEmpty(message = "email is required")
   @Email
   private String email;

   @NotEmpty(message = "password is required")
   private String password;

   @NotEmpty(message = "role can't be empty!")
   private String role;

   public void setRole(String role){
      this.role = role;
   }

   public String getRole(){
       return this.role;
   }

   private LocalDateTime lastUpdate = LocalDateTime.now();

   public LocalDateTime getLastUpdate() {
      return lastUpdate;
   }

   public void setLastUpdate(LocalDateTime lastUpdate) {
      this.lastUpdate = lastUpdate;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }


}
