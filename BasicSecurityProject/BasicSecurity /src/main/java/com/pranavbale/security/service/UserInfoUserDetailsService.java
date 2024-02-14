package com.pranavbale.security.service;

import com.pranavbale.security.config.UserInfoUserDetails;
import com.pranavbale.security.entity.UserInfo;
import com.pranavbale.security.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // gating an Optional user Info form the database
        Optional<UserInfo> userInfo = Optional.of(userRepository.findByUserName(username));

        // convert a user info into the userDetails
        return userInfo.map(UserInfoUserDetails::new).orElseThrow(()->new RuntimeException("User Not found with name : " + username));
    }
}
