package com.example.springbootfirst.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    private int EmpId;
    private String Name;
    private String Job;
    public int getEmpId() {
        return EmpId;
    }
}
