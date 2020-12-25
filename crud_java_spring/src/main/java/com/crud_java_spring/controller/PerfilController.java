package com.crud_java_spring.controller;

import java.util.List;

import com.crud_java_spring.model.Perfil;
import com.crud_java_spring.repostiory.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/perfis")
public class PerfilController {


    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> buscarTodos(){
        return perfilRepository.findAll();
    }
    @PostMapping
    public Perfil salvarPerfil(@RequestBody Perfil perfil){
        return perfilRepository.save(perfil);
    }
    @PutMapping
    public Perfil atualizarPerfil(@RequestBody Perfil perfil){
        return perfilRepository.save(perfil);
    }
   
    @DeleteMapping("/{id}")
	public void excluirPerfil(@PathVariable("id") Long id){
		perfilRepository.deleteById(id);
	}
    
}
