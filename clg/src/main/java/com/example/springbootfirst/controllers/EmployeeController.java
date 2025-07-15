package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService hws;


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    public String route(){
        return "Welcome to SpringBoot Security";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee")
    public List<RegisterDetails> getMethod(){
        return hws.getMethod();
    }


    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public RegisterDetails getEmployeeById(@PathVariable int empId){
        System.out.println();
        return hws.getEmployeeById(empId);
    }

    @GetMapping("/employee/role/{role}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public RegisterDetails getEmployeeByRole(@PathVariable String role){
        return hws.getEmployeeByRole(role);
    }


//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public String postMethod(@RequestBody RegisterDetails e){
//        return hws.postMethod(e);
//    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee")
    public String postMethod(@RequestBody UserDetailsDto employee){
        return hws.addNewEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employee/{empId}")
    public String putMethod(@PathVariable int empId,@RequestBody UserDetailsDto details){
        return hws.updateEmployee(empId,details);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/emp/{empID}")
    public String deleteMethod(@PathVariable int empID){
        return hws.deleteMethod(empID);
    }






//    @GetMapping
//    public List<Employee> getMethod(){
//        return hws.getMethod();
//    }
//
//    @GetMapping("/{EmpId}")
//    public Employee getEmployeeById(@PathVariable int EmpId){
//        return hws.getEmployeeById(EmpId);
//    }
//
//    @PostMapping
//    public void postMethod(@RequestBody Employee emp){
//        hws.postMethod(emp);
//    }
//
//    @PutMapping
//    public String UpdateRecord(@RequestBody Employee employee){
//        return hws.UpdateRecord(employee);
//    }
//
//    @DeleteMapping("/{EmpId}")
//    public String deleteMethod(@PathVariable int EmpId){
//        return hws.deleteMethod(EmpId);
//    }


}
