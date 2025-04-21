package com.codewithmosh.store.controllers;


import com.codewithmosh.store.dtos.LoginRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import com.codewithmosh.store.services.JwtService;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor

@RequestMapping("/auth")
public class AuthController {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        var  userData =  userMapper.LoginUser(request);
        System.out.println(request);
        System.out.println(userData);
          boolean isValid = userRepository.existsByEmail(userData.getEmail());

    User user = userRepository.findByEmail(userData.getEmail());
          if (!isValid) {
             return  ResponseEntity.status(401).body(
                       Map.of(
                               "message", "Incorrect email or password"
                       )
               );
          }
          boolean isValidPassword = passwordEncoder.matches(request.getPassword(), user.getPassword());
          if (!isValidPassword) {
          return    ResponseEntity.status(401).body(
                      Map.of(
                              "message", "Incorrect email or password"
                      )
              );
          }
          var token =  jwtService.generateToken(user.getEmail());

        String token1 = token;
        return ResponseEntity.
                  status(200).body(
                          Map.of( "token", token1)
                );
        }
}
