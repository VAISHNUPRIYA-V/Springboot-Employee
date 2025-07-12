package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails,Integer> {

    RegisterDetails findByEmail(String email);


    @Query("SELECT r FROM RegisterDetails r JOIN r.roles role WHERE role.roleName = :roleName")
    Optional<RegisterDetails> findByRole(@Param("roleName") String roleName);
    //Object findByRole(String role);
}
