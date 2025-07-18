package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.Task;
import com.example.springbootfirst.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/id/{empId}/tasks")
    public List<Task> getTasksByEmployee(@PathVariable int empId) {
        return t.getTasksByEmployeeId(empId);
    }

    @PutMapping("/status/{taskId}")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Integer taskId, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        boolean updated = t.updateTaskStatus(taskId, newStatus);
        if (updated) {
            return ResponseEntity.ok("Status updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }

}
