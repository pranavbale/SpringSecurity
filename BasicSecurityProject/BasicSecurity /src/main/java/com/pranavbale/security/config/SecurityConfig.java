package com.pranavbale.security.config;

import com.pranavbale.security.service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    public UserDetailsService userDetailsService() {

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
                                .requestMatchers(HttpMethod.POST,"/person/createUser").permitAll()
                                .requestMatchers(HttpMethod.GET, "/person/welcome").permitAll()
                                // if URL with this type then firstly authentication is required
                                .requestMatchers("/person/**").authenticated()
                                // only admin and user is allowed
//                              .requestMatchers("/person/**").hasAnyRole("ADMIN", "USER")
                                // only admin is allowed for this url
//                              .requestMatchers("/person/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }


    // for creating a user detail service need to create a bean of authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // create a Dao Authentication Provider Object
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // set a user detail service to authentication provider
        authenticationProvider.setUserDetailsService(userDetailsService());
        // set a password encoder for the authentication provider
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    // use a passwordEncoder for encrypt and decrypt the password
    @Bean
    public PasswordEncoder passwordEncoder() {
        // return decrypt password encoder
        return new BCryptPasswordEncoder();
    }
}
