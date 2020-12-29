package com.crud_java_spring.controller;

import com.crud_java_spring.DTO.UsuarioDTO;
import com.crud_java_spring.exception.ErroAutenticacao;
import com.crud_java_spring.exception.RegraNegocioException;
import com.crud_java_spring.model.Usuario;
import com.crud_java_spring.service.CepService;
import com.crud_java_spring.service.UsuarioService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	
	private UsuarioService usuarioService;

	@Autowired
	private CepService cepService;



	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto){
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);

		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		

	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto){

		Usuario usuario = Usuario.builder()
				.nome(dto.getNome())
				.email(dto.getEmail())
				.cpf(dto.getCpf())
				.senha(dto.getSenha())
				.telefone(dto.getTelefone())
				.endereco(dto.getEndereco())
				.build();

		try {
			Usuario usuarioSalvo = usuarioService.salvaUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	
}



