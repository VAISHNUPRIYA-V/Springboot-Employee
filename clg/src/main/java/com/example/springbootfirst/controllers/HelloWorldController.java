package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.Employee;
import com.example.springbootfirst.services.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class HelloWorldController {
    @Autowired
    private HelloWorldService hws;


    @GetMapping
    public List<Employee> getMethod(){
        return hws.getMethod();
    }

    @GetMapping("/{EmpId}")
    public Employee getEmployeeById(@PathVariable int EmpId){
        return hws.getEmployeeById(EmpId);
    }

    @PostMapping
    public void postMethod(@RequestBody Employee emp){
        hws.postMethod(emp);
    }

    @PutMapping
    public String UpdateRecord(@RequestBody Employee employee){
        return hws.UpdateRecord(employee);
    }

    @DeleteMapping("/{EmpId}")
    public String deleteMethod(@PathVariable int EmpId){
        return hws.deleteMethod(EmpId);
    }


}
