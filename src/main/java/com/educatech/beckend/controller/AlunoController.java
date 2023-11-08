package com.educatech.beckend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educatech.beckend.model.Aluno;
import com.educatech.beckend.model.Professor;
import com.educatech.beckend.repository.AlunoRepository;
import com.educatech.beckend.repository.ProfessorRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/alunos")
@AllArgsConstructor
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    @PostMapping("/cadastrar")
    public boolean cadastrar(@RequestBody Aluno aluno){
      java.util.List<Aluno> existentAluno = alunoRepository.findUsersByEmail(aluno.getEmail());
      java.util.List<Professor> existentProfessor = professorRepository.findUsersByEmail(aluno.getEmail());

      if(!existentAluno.isEmpty() || !existentProfessor.isEmpty()){
        return false;
      }

      Aluno newAluno = new Aluno();
      newAluno.setNome(aluno.getNome());
      newAluno.setCpf(aluno.getCpf());
      newAluno.setEmail(aluno.getEmail());
      newAluno.setEscolarizacao(aluno.getEscolarizacao());
      newAluno.setEndereco(aluno.getEndereco());
      newAluno.setTelefone(aluno.getTelefone());
      newAluno.setSenha(aluno.getSenha());

      alunoRepository.save(newAluno);

      
      return true;
    }

    
    


}
