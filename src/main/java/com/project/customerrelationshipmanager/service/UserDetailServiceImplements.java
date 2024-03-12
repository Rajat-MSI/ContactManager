package com.project.customerrelationshipmanager.service;

import com.project.customerrelationshipmanager.model.User;
import com.project.customerrelationshipmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImplements implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        System.out.println(username);
        User user = userRepository.getUserByUserEmail(username);
        System.out.println(user);
        if(user == null)
        {
            throw new UsernameNotFoundException("Could not found user!!");
        }
         return new UserDetailsImplements(user);
    }
}
