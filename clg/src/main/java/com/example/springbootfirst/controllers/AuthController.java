package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.AuthResponse;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDetailsDto register){
        return authService.addNewEmployee(register);
    }

//    @GetMapping
//    public List<RegisterDetails> getDetails(){
//        return authService.getDetails();
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody RegisterDetails login){
        AuthResponse response = authService.authenticate(login);  // or employeeService if it’s there
        return ResponseEntity.ok(response);
    }
}
