package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Task;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    public String assignTaskById(int empId, Task task) {
        RegisterDetails user = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        task.setAssignedEmployee(user);
        taskRepository.save(task);
        return "Task assigned to employee ID: " + empId;
    }

    public List<Task> getTasksByEmployeeId(int empId) {
        return taskRepository.findByAssignedEmployee(empId);
    }

    public boolean updateTaskStatus(Integer taskId, String status) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(status);
            taskRepository.save(task);
            return true;
        } else {
            return false;
        }
    }


}