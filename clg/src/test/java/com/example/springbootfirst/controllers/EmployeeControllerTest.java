package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoute(){
        String result = employeeController.route();;
        assertEquals("Welcome to SpringBoot Security",result);
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1=new RegisterDetails();
        RegisterDetails emp2=new RegisterDetails();
        when(employeeService.getMethod()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result = employeeController.getMethod();
        assertEquals(2,result.size());
    }

    @Test
    void testGetEmployeeById() {
        RegisterDetails emp = new RegisterDetails();
        emp.setEmpId(1);
        emp.setName("Test Employee");

        when(employeeService.getEmployeeById(1)).thenReturn(emp);
        RegisterDetails result = employeeController.getEmployeeById(1);

        assertNotNull(result);
        assertEquals("Test Employee", result.getName());
    }

    @Test
    void testGetEmployeeByRole() {
        RegisterDetails emp = new RegisterDetails();
        emp.setEmpId(1);
        emp.setName("Admin");

        when(employeeService.getEmployeeByRole("ADMIN")).thenReturn(emp);
        RegisterDetails result = employeeController.getEmployeeByRole("ADMIN");

        assertNotNull(result);
        assertEquals("Admin", result.getName());
    }

    @Test
    void testPostMethod() {
        UserDetailsDto dto = new UserDetailsDto();
        dto.setEmpId(1);
        dto.setName("John");

        when(employeeService.addNewEmployee(dto)).thenReturn("Employee Added Successfully");
        String result = employeeController.postMethod(dto);

        assertEquals("Employee Added Successfully", result);
    }

    @Test
    void testPutMethod() {
        UserDetailsDto dto = new UserDetailsDto();
        dto.setName("Updated Name");

        when(employeeService.updateEmployee(1, dto)).thenReturn("Employee updated successfully");
        String result = employeeController.putMethod(1, dto);

        assertEquals("Employee updated successfully", result);
    }

    @Test
    void testDeleteMethod() {
        // Arrange
        int empId = 1;
        String expectedResponse = "Employee deleted successfully";

        when(employeeService.deleteMethod(empId)).thenReturn(expectedResponse);

        // Act
        String actualResponse = employeeController.deleteMethod(empId);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }


}