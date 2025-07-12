package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.Task;
import com.example.springbootfirst.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService t;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/id/{empId}")
    public String assignTaskById(@PathVariable int empId, @RequestBody Task task){
        return t.assignTaskById(empId,task);
    }
}
