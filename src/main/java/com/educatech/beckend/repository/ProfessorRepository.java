package com.educatech.beckend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educatech.beckend.model.Professor;

@Repository
public interface ProfessorRepository  extends JpaRepository<Professor, Long>{

    @Query("SELECT DISTINCT p FROM Professor p WHERE p.id = :id")
    Professor findUserById(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Professor p WHERE p.email LIKE :email")
    java.util.List<Professor> findUsersByEmail(@Param("email") String email);

    @Query("SELECT DISTINCT p FROM Professor p WHERE p.email = :email and p.senha = :senha")
    java.util.List<Professor> findUsersByEmailandPass(@Param("email") String email, @Param("senha") String senha);
    
}
