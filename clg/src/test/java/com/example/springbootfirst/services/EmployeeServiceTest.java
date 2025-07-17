package com.example.springbootfirst.services;


import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.RolesRepository;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.models.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
    @Mock
    RegisterDetailsRepository registerDetailsRepository;

    @Mock
    RolesRepository rolesRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        employeeService.rolesRepository = rolesRepository;
        employeeService.passwordEncoder = passwordEncoder;
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1=new RegisterDetails();
        RegisterDetails emp2=new RegisterDetails();
        when(registerDetailsRepository.findAll()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result= employeeService.getMethod();
        assertEquals(2,result.size());

    }

    @Test
    void testAddNewEmployee() {
        UserDetailsDto dto = new UserDetailsDto();
        dto.setEmpId(4);
        dto.setName("Priya");
        dto.setEmail("priya@example.com");
        dto.setPassword("priya");
        dto.setUserName("priya");
        dto.setRoleNames(Set.of("USER"));

        Roles mockRole = new Roles();
        mockRole.setRoleName("USER");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(rolesRepository.findByRoleName("USER")).thenReturn(java.util.Optional.of(mockRole));

        String result = employeeService.addNewEmployee(dto);
        assertEquals("Employee Registered Successfully", result);
    }

    @Test
    void testUpdateEmployee() {
        RegisterDetails existing = new RegisterDetails();
        existing.setEmpId(1);
        existing.setName("Old Name");

        UserDetailsDto dto = new UserDetailsDto();
        dto.setName("Watson");
        dto.setEmail("watson@example.com");
        dto.setPassword("watson");
        dto.setUserName("watson");

        when(registerDetailsRepository.findById(1)).thenReturn(java.util.Optional.of(existing));

        String result = employeeService.updateEmployee(1, dto);
        assertEquals("Employee updated successfully", result);
    }

    @Test
    void testDeleteEmployee() {
        String result = employeeService.deleteMethod(1);
        assertEquals("Employee deleted successfully", result);
    }

}