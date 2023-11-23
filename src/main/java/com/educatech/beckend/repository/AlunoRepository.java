package com.educatech.beckend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educatech.beckend.model.Aluno;


@Repository
public interface AlunoRepository  extends JpaRepository<Aluno, Long>{

    @Query("SELECT DISTINCT p FROM Aluno p WHERE p.id = :id")
    Aluno findUserById(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Aluno p WHERE p.email LIKE :email")
    java.util.List<Aluno> findUsersByEmail(@Param("email") String email);

    @Query("SELECT DISTINCT p FROM Aluno p WHERE p.email = :email and p.senha = :senha")
    java.util.List<Aluno> findUsersByEmailandPass(@Param("email") String email, @Param("senha") String senha);

}
