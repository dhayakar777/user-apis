package com.techvedika.ApiDemo.controllers;


import com.techvedika.ApiDemo.dtos.UserDto;
import com.techvedika.ApiDemo.exceptions.InvalidUserNameOrPasswordException;
import com.techvedika.ApiDemo.dtos.LoginRequest;
import com.techvedika.ApiDemo.models.User;
import com.techvedika.ApiDemo.services.UserService;
import com.techvedika.ApiDemo.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/demo/users")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public User create(@Valid @RequestBody final UserDto userDto) {
      return userService.createUser(mapper.map(userDto, User.class));
    }

    @PostMapping("/generate-token")
    public String generateToken(@Valid @RequestBody LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                    loginRequest.getPassword()));
        } catch (InvalidUserNameOrPasswordException e) {
            throw new InvalidUserNameOrPasswordException("Invalid User name or password");
        }
        return jwtUtil.generateToken(loginRequest.getUserName());
    }
    @GetMapping("/{userId}")
    public User get(@PathVariable final Integer userId) {
        return userService.getUser(userId);
    }

    @GetMapping()
    public Page<User> find(@PageableDefault Pageable pageable){
        return userService.getAll(pageable);
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
