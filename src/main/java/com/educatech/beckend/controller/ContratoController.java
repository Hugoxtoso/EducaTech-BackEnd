package com.educatech.beckend.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educatech.beckend.model.Contrato;
import com.educatech.beckend.model.User;
import com.educatech.beckend.repository.ContratoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/contrato")
@AllArgsConstructor
public class ContratoController {

    private final ContratoRepository contratoRepository;


    @PostMapping("/cadastrar")
    public boolean cadastrar(@RequestBody Contrato contrato){

      Contrato newContrato = new Contrato();
      newContrato.setTitulo(contrato.getTitulo());
      newContrato.setId_usuario(contrato.getId_usuario());
      newContrato.setId_professor(contrato.getId_professor());
      newContrato.setDescricao(contrato.getDescricao());
      newContrato.setValor(contrato.getValor());
      newContrato.setEstado(contrato.getEstado());

      contratoRepository.save(newContrato);

      return true;
    }

    @PutMapping("/editar")
    public boolean editar(@RequestBody Contrato contrato){

      try {
        contratoRepository.save(contrato);
        return true;
        
      } catch (Exception e) {
        return false;
      }
      
    }

    @PostMapping("/listarPorId")
    public java.util.List<Contrato> contratosPorId( @RequestBody User user){
      try {
        if (user.getTipo().equals("professor")) 
          return contratoRepository.findContratosById_professor((user.getId()));
      
        if (user.getTipo().equals("aluno")) 
          return contratoRepository.findContratosById_usuario((user.getId()));

        return null;

      } catch (Exception e) {
        return null;
      }
    }

    @PostMapping("/buscarid")
    public Contrato buscarPorID(@RequestBody int n){
      long id = (long) n;
      return contratoRepository.findContratoById(id);
      
    }

    }




