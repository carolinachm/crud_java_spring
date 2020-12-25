package com.crud_java_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.crud_java_spring.exception.UsuarioNãoEncontradoException;
import com.crud_java_spring.model.Usuario;
import com.crud_java_spring.repostiory.UsuarioRepository;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	public UsuarioRepository usuarioRepository;


	@GetMapping
	public List<Usuario> buscarTodos(){
		return usuarioRepository.findAll();
	}
	
	@PostMapping("/auth")
	public List<Usuario> autenticar(@RequestBody Usuario usuario ) throws UsuarioNãoEncontradoException {

		List<Usuario> usuarios = usuarioRepository.findByNomeAndSenha(usuario.getNome(), usuario.getSenha());

		if(usuarios == null || usuarios.size() == 0){
			throw new UsuarioNãoEncontradoException();
		}else{
			return usuarios;
		}
	}

	@PostMapping
	public Usuario salvarUsuairo(@RequestBody Usuario usuario){
		return usuarioRepository.save(usuario);
	}
	@PutMapping()
	public Usuario alterarUsuairo(@RequestBody Usuario usuario){
		return usuarioRepository.save(usuario);
	}
	@DeleteMapping("/{id}")
	public void excluirUsuairo(@PathVariable("id") Long id){
		usuarioRepository.deleteById(id);
	}
	@GetMapping("/q/perfil")
	List<Usuario> buscarPorPerfil(@RequestParam ("descricao") String descricao){
		return usuarioRepository.findByPerfilDescricao(descricao);
	}

	
	}



