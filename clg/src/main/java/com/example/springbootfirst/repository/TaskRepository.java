package com.example.springbootfirst.repository;
import com.example.springbootfirst.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
