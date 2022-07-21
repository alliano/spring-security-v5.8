package com.spring.model.entitys;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.model.entitys.role.Role;


@Entity()
@Table(name = "users")
public class User implements UserDetails {
   
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @NotEmpty(message = "name is required")
   private String name;

   @NotEmpty(message = "email is required")
   @Email
   @Column(unique = true)
   private String email;

   @NotEmpty(message = "password is")
   private String password;

   @Enumerated(EnumType.STRING)
   private Role role;


   public User(){}


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


   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role.name());
      return Collections.singletonList(authority);
   }


   @Override
   public String getUsername() {
      return this.email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return false;
   }


   @Override
   public boolean isAccountNonLocked() {
      return false;
   }


   @Override
   public boolean isCredentialsNonExpired() {
      return false;
   }


   @Override
   public boolean isEnabled() {
      return false;
   }
   
}
