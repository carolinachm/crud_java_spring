package com.crud_java_spring.repository;

import java.util.Optional;

import com.crud_java_spring.model.Usuario;
import com.crud_java_spring.repostiory.UsuarioRepository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest // cria uma instancia do banco de dados
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  TestEntityManager em;

  @Test
  public void deveVerificarAExistenciaDeUmEmail() {

    // cenário
    Usuario usuario = criarUsuario();
    em.persist(usuario);
    // ação
    boolean result = usuarioRepository.existsByEmail("jao@jao.com");

    // verificação
    Assertions.assertThat(result).isTrue();

  }

  @Test
  public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastroComEmail() {

    // cenário

    // ação
    boolean result = usuarioRepository.existsByEmail("teste@teste.com");
    // verificação
    Assertions.assertThat(result).isFalse();

  }

  @Test
  public void devePersisitirUmUsuarioNaBaseDeDados() {
    // cenario
    Usuario usuario = criarUsuario();
    // acao
    Usuario usuarioSalvo = usuarioRepository.save(usuario);

    Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
  }

  @Test
  public void deveBuscarUmUsuarioPorEmail() {
    // cenario
    Usuario usuario = criarUsuario();
    em.persist(usuario);
    // acao
    Optional<Usuario> result = usuarioRepository.findByEmail("jao@jao.com");

    Assertions.assertThat(result.isPresent()).isTrue();

  }
  @Test
  public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExistir() {
    // cenario
  
    // acao
    Optional<Usuario> result = usuarioRepository.findByEmail("jao@jao.com");

    Assertions.assertThat(result.isPresent()).isFalse();

  }

  public static Usuario criarUsuario() {
    return Usuario.builder().nome("jao").senha("senha").email("jao@jao.com").build();
  }

}
