package com.educatech.beckend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educatech.beckend.model.Aluno;
import com.educatech.beckend.model.Professor;
import com.educatech.beckend.repository.AlunoRepository;
import com.educatech.beckend.repository.ProfessorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/shared")
@AllArgsConstructor
public class SharedController {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    @PostMapping("/logar")
    public Object logar(@RequestBody String body){

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map;
        try {
            map = mapper.readValue(body, Map.class);                       
            String email = map.get("email");
            String senha = map.get("senha");
            java.util.List<Aluno> existentAluno = alunoRepository.findUsersByEmailandPass(email, senha);
            java.util.List<Professor> existentProfessor = professorRepository.findUsersByEmailandPass(email, senha);

            if (!existentAluno.isEmpty()) {
                return loginAluno(existentAluno.get(0).getId());
            }
            if(!existentProfessor.isEmpty()){
                return loginProfessor(existentProfessor.get(0).getId());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Aluno loginAluno(Long id){
        return alunoRepository.findUserById(id);
    }

    public Professor loginProfessor(Long id){
        return professorRepository.findUserById(id);
    }
}
