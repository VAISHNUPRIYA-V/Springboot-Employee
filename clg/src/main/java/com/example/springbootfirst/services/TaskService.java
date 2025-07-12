package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Task;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    public String assignTaskById(int empId, Task task){
        RegisterDetails user=registerDetailsRepository.findById(empId)
                .orElseThrow(()->new RuntimeException("Employee not found"));
        task.setAssignedEmployee(user);
        taskRepository.save(task);
        return ("Task assigned to employee id: "+empId);
    }
}
