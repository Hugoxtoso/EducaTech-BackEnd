package com.educatech.beckend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educatech.beckend.model.Contrato;

@Repository
public interface ContratoRepository  extends JpaRepository<Contrato, Long>{

    @Query("SELECT DISTINCT p FROM Contrato p WHERE p.id LIKE :id")
    Contrato findContratoById(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Contrato p WHERE p.id_usuario = :id_usuario")
    java.util.List<Contrato> findContratosById_usuario(@Param("id_usuario") int id_usuario);

    @Query("SELECT DISTINCT p FROM Contrato p WHERE p.id_professor = :id_professor")
    java.util.List<Contrato> findContratosById_professor(@Param("id_professor") int id_professor);


    
}

