package com.codewithmosh.store.controllers;


import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor

@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public  Iterable<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ) {
        if (!Set.of("name", "email").contains(sort))
            sort = "name";

            return userRepository.findAll(
                            Sort.by(sort)
                    ).stream()
                    .map(userMapper::toDto).toList();
        }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id){
         var user = userRepository.findById(id).orElse(null);
         if(user == null) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(userMapper.toDto(user));
    }
    @PostMapping
    public ResponseEntity<?> createUser(  @RequestBody @Valid  RegisterUserRequest userDto){
        var newUser = userMapper.toEntity(userDto);
        if(userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(409).body(
                    Map.of("email", "Email already taken")
            );
        }
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(newUser);
        System.out.println(newUser);
        return ResponseEntity.ok(userMapper.toDto(newUser));
    }
    @PutMapping("{id}")
    public  ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UpdateUserRequest userDto){
         var user = userRepository.findById(id).orElse(null);
         if(user == null) {
             return ResponseEntity.notFound().build();
         }
         user = userMapper.UpdateUser(
                 userDto
         );
         user.setId(id);
         userRepository.save(user);
         return ResponseEntity.ok(userMapper.toDto(user));
    }
     @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser( @PathVariable long id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
     }

}





