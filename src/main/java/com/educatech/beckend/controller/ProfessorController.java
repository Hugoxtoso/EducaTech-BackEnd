package com.educatech.beckend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educatech.beckend.model.Aluno;
import com.educatech.beckend.model.Professor;
import com.educatech.beckend.repository.AlunoRepository;
import com.educatech.beckend.repository.ProfessorRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/professores")
@AllArgsConstructor
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    
    


    @GetMapping("/list")
    public java.util.List<Professor> list() {
      return professorRepository.findAll();
    }

    @PostMapping("/cadastrar")
    public boolean cadastrar(@RequestBody Professor prof){
      java.util.List<Professor> existentProfessor = professorRepository.findUsersByEmail(prof.getEmail());
      java.util.List<Aluno> existentAluno = alunoRepository.findUsersByEmail(prof.getEmail());
      
      if(!existentProfessor.isEmpty() || !existentAluno.isEmpty()){
        return false;
      }

      Professor newProfessor = new Professor();
      newProfessor.setNome(prof.getNome());
      newProfessor.setCpf(prof.getCpf());
      newProfessor.setDisponibilidade(prof.getDisponibilidade());
      newProfessor.setEmail(prof.getEmail());
      newProfessor.setFaculdade(prof.getFaculdade());
      newProfessor.setEspecializacao(prof.getEspecializacao());
      newProfessor.setImg(prof.getImg());
      newProfessor.setTelefone(prof.getTelefone());
      newProfessor.setSenha(prof.getSenha());
      newProfessor.setAvaliacao(0);
      newProfessor.setQuantidadeavaliacao(0);

      professorRepository.save(newProfessor);

      
      return true;
    }

    @PostMapping("/buscarid")
    public Professor buscarPorID(@RequestBody int n){
      long id = (long) n;
      return professorRepository.findUserById(id);
      
    }

    @PutMapping("/editar")
    public boolean editar(@RequestBody Professor professor){
      Professor mesmoProfessor = professorRepository.findUserById(professor.getId());

      if(!mesmoProfessor.getEmail().equals(professor.getEmail())){
        java.util.List<Aluno> existentAluno = alunoRepository.findUsersByEmail(professor.getEmail());
        java.util.List<Professor> existentProfessor = professorRepository.findUsersByEmail(professor.getEmail());

        if(!existentAluno.isEmpty() || !existentProfessor.isEmpty()){
          return false;
        }
      }

      professorRepository.save(professor);
    
      return true;
    }

    
}
