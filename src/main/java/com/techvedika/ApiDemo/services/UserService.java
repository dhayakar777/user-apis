package com.techvedika.ApiDemo.services;


import com.techvedika.ApiDemo.exceptions.EntityExistsException;
import com.techvedika.ApiDemo.exceptions.UserNotFoundException;
import com.techvedika.ApiDemo.models.User;
import com.techvedika.ApiDemo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmailId(user.getEmailId());
        if (optionalUser.isPresent()) {
            throw new EntityExistsException(String.format("User already exist with email Id %s", user.getEmailId()));
        } else {
            log.info("New user created successfully");
            return userRepository.save(user);
        }
    }

    public User getUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException(String.format("User not found with the given id %s", userId));
        }
    }

    public void deleteUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
           userRepository.deleteById(userId);
           log.warn("User Deleted successfully");
        } else {
            throw new UserNotFoundException(String.format("User not found with the given id %s", userId));
        }
    }

    public User updateUser(User user, Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            optionalUser.get().setId(userId);
            optionalUser.get().setName(user.getName());
            optionalUser.get().setPassword(user.getPassword());
            optionalUser.get().setEmailId(user.getEmailId());
            optionalUser.get().setCity(user.getCity());
            optionalUser.get().setMobileNo(user.getMobileNo());
                      log.info("User updated successfully");
            return userRepository.save(optionalUser.get());
        } else {
            throw new UserNotFoundException(String.format("User not found with the given id %s", userId));
        }
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
