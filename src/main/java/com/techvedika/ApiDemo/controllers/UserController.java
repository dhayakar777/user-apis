package com.techvedika.ApiDemo.controllers;


import com.techvedika.ApiDemo.dtos.UserDto;
import com.techvedika.ApiDemo.models.User;
import com.techvedika.ApiDemo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/demo/users")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;


    @PostMapping
    public User create(@Valid @RequestBody final UserDto userDto) {
      return userService.createUser(mapper.map(userDto, User.class));
    }

    @GetMapping("/{userId}")
    public User get(@PathVariable final Integer userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable final Integer userId) {
        userService.deleteUser(userId);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public User update(@RequestBody User user, @PathVariable final Integer userId) {
        return userService.updateUser(user, userId);
    }

}
