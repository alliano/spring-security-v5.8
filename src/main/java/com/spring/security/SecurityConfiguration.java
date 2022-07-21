package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.service.UserService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfiguration {
   
   @Autowired
   private UserService userService;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
      httpSecurity.csrf().disable()
      .authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/register").permitAll()
      .and().authorizeRequests().anyRequest().authenticated().and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().authenticationProvider(daoAuthenticationProvider());
      return httpSecurity.build();
   }

   public DaoAuthenticationProvider daoAuthenticationProvider(){
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      daoAuthenticationProvider.setUserDetailsService(this.userService);
      daoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
      return daoAuthenticationProvider;
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration conf)throws Exception{
      return conf.getAuthenticationManager();
   }

   @Bean
   public WebMvcConfigurer webMvcConfigurer(){
      return new WebMvcConfigurer() {
         
         @Override
         public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/")
            .allowedOrigins("*")
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowedOriginPatterns("*","**");
            WebMvcConfigurer.super.addCorsMappings(registry);
         }
      };
   }

}
