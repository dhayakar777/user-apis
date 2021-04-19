package com.techvedika.ApiDemo.services;

import com.techvedika.ApiDemo.exceptions.UserNotFoundException;
import com.techvedika.ApiDemo.models.User;
import com.techvedika.ApiDemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmailId(userName);
        if (!user.isPresent()) {
          throw new UserNotFoundException(String.format("No record found with given user name %s", userName));
        }
        return new org.springframework.security.core.userdetails.User(user.get().getName(), user.get().getPassword(), new ArrayList<>());
    }
}
