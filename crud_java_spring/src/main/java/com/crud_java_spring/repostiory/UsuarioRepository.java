package com.crud_java_spring.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.crud_java_spring.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmail(String email);

	Optional<Usuario> findByEmail(String email);

	

}
