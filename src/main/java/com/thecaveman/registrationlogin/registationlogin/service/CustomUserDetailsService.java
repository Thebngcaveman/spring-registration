package com.thecaveman.registrationlogin.registationlogin.service;

import com.thecaveman.registrationlogin.registationlogin.models.CustomUserDetails;
import com.thecaveman.registrationlogin.registationlogin.models.User;
import com.thecaveman.registrationlogin.registationlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =repository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("The caveman list has no such email!!");
        }
        return new CustomUserDetails(user);
    }
}
