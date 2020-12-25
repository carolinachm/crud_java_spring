package com.crud_java_spring.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.crud_java_spring.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByNomeAndSenha(String nome, String senha);

	List<Usuario> findByPerfilDescricao(String descricao);

    @Query("select u from Usuario u where u.perfil.descricao= :desc ")
	List<Usuario> buscarPorPerfil(@Param("desc") String desc);

	

}
