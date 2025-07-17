package com.example.springbootfirst.services;

import com.example.springbootfirst.models.JwtResponse;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.RegisterRepository;
import com.example.springbootfirst.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springbootfirst.jwt.JwtTokenProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    RegisterDetailsRepository repo;

    @Autowired
    RolesRepository rolerepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RegisterRepository registerRepository;


    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails=new RegisterDetails();

        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles = new HashSet<>();
        for(String roleName: register.getRoleNames()){
            Roles role = rolerepo.findByRoleName(roleName)
                            .orElseThrow(()->new RuntimeException("Role not found" + roleName));
                    roles.add(role);
        }
        registerDetails.setRoles(roles);
        repo.save(registerDetails);
        return "Employee Registered Successfully";
    }






//    public String authenticate(RegisterDetails login) {
//        RegisterDetails user=repo.findByEmail(login.getEmail());
//        if(user!=null){
//            //if(Objects.equals(user.getPassword(),login.getPassword())){
//            if(passwordEncoder.matches(login.getPassword(),user.getPassword())){
//                return "Login Successful";
//            }else{
//                return "Login not successful";
//            }
//        }
//        else{
//            return "Login not successful";
//        }
//
//    }
//
//    public List<RegisterDetails> getDetails() {
//        return repo.findAll();
//    }



    public JwtResponse authenticate(RegisterDetails login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(), login.getPassword()));

        String token = jwtTokenProvider.generateToken(authentication);
        RegisterDetails user = registerRepository.findByUserName(login.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Set<String> roleNames = user.getRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toSet());

        return new JwtResponse(token, user.getUserName(), roleNames);
    }
}
