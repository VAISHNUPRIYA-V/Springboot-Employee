package com.example.springbootfirst.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String task;

    public RegisterDetails getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(RegisterDetails assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    @ManyToOne
    @JoinColumn(name="emp_id")
    private RegisterDetails assignedEmployee;


}
