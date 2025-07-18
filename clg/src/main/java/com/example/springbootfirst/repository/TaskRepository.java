package com.example.springbootfirst.repository;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query("SELECT t FROM Task t WHERE t.assignedEmployee.id = :empId")
    List<Task> findByAssignedEmployee(@Param("empId") int empId);

}
