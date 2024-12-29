package com.bookish.Bookish.controller;

import com.bookish.Bookish.model.User;
import com.bookish.Bookish.model.dto.UserCreationDto;
import com.bookish.Bookish.security.SecurityConfig;
import com.bookish.Bookish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private SecurityConfig security;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = service.findAll();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = service.findById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserCreationDto> createUser(@RequestBody User user){
        user.setPassword(security.passwordEncoder().encode(user.getPassword()));
        User createdUser = service.create(user);
        UserCreationDto responseUser = new UserCreationDto(
                createdUser.getUsername(),
                createdUser.getEmail(),
                createdUser.getRole()
        );
        return ResponseEntity.ok(responseUser);
    }
    @PutMapping
    public ResponseEntity<User> updateUser(@PathVariable Long id
                                            , @RequestBody User userDetails){
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()){
            User userToUpdate = userOptional.get();
            userToUpdate.setUsername(userDetails.getUsername());
            userToUpdate.setEmail(userDetails.getEmail());
            userToUpdate.setPassword(userDetails.getPassword());
            userToUpdate.setRole(userDetails.getRole());
            User updatedUser = service.update(userToUpdate);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        Optional<User> user = service.findById(id);
        if (user.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
