package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    RegisterDetailsRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String addNewEmployee(RegisterDetails register) {
        RegisterDetails registerDetails=new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setGender(register.getGender());
        registerDetails.setRole(register.getRole());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setDateOfBirth(register.getDateOfBirth());
        registerDetails.setEmpName(register.getEmpName());
        repo.save(register);
        return "Employee Registered Successfully";
    }

    public String authenticate(RegisterDetails login) {
        RegisterDetails user=repo.findByEmail(login.getEmail());
        if(user!=null){
            //if(Objects.equals(user.getPassword(),login.getPassword())){
            if(passwordEncoder.matches(login.getPassword(),user.getPassword())){
                return "Login Successful";
            }else{
                return "Login not successful";
            }
        }
        else{
            return "Login not successful";
        }

    }

    public List<RegisterDetails> getDetails() {
        return repo.findAll();
    }
}
