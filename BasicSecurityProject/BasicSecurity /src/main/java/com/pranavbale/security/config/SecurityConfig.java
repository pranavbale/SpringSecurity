package com.pranavbale.security.config;

import com.pranavbale.security.service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity          // enable a web security
public class SecurityConfig {

    // create a Role base Authentication
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // create a hard coded user

//        // care a user
//        UserDetails user1 = User.withUsername("appu")       // set a username
//                .password(passwordEncoder.encode("appu"))                           // set a password
//                .roles("ADMIN")                             // set a role
//                .build();                                   // build a user
//
//        // care a user
//        UserDetails user2 = User.withUsername("apeksha")       // set a username
//                .password(passwordEncoder.encode("apeksha"))                           // set a password
//                .roles("USER")                                 // set a role
//                .build();                                      // build a user
//
//        // both the user store the user into the memory
//        return new InMemoryUserDetailsManager(user1, user2);


        // fetch the user form the database
        return new UserInfoUserDetailsService();
    }

    // Role based Authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                // permit all the request without Authentication
                                .requestMatchers("/person/welcome", "/user/**").permitAll()
                                // if URL with this type then firstly authentication is required
                                .requestMatchers("/person/**").authenticated()
                                // only admin and user is allowed
//                              .requestMatchers("/person/**").hasAnyRole("ADMIN", "USER")
                                // only admin is allowed for this url
//                              .requestMatchers("/person/**").hasRole("ADMIN")
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    // use a passwordEncoder for encrypt and decrypt the password
    @Bean
    public PasswordEncoder passwordEncoder() {
        // return decrypt password encoder
        return new BCryptPasswordEncoder();
    }
}
