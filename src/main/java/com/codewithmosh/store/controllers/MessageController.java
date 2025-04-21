package com.codewithmosh.store.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @RequestMapping("/api")
    public String hello(){
        return "Hello World";
    }
}
