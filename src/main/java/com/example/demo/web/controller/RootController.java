package com.example.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/health")
    public String healthCheck(){
        return "imhealth";
    }



}
