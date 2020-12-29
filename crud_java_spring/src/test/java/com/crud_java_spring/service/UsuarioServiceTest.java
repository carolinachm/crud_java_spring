package com.crud_java_spring.service;

import java.util.Optional;

import com.crud_java_spring.exception.ErroAutenticacao;
import com.crud_java_spring.exception.RegraNegocioException;
import com.crud_java_spring.model.Usuario;
import com.crud_java_spring.model.Usuario.UsuarioBuilder;
import com.crud_java_spring.repostiory.UsuarioRepository;
import com.crud_java_spring.service.implement.UsuarioServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @SpyBean
    private UsuarioServiceImpl usuarioService;
    @MockBean
    private UsuarioRepository usuarioRepository;

   
    @Test(expected = Test.None.class)
    public void deveSalvarUmUsuario(){
        //cenario
        Mockito.doNothing().when(usuarioService).validarEmail(Mockito.anyString());
        Usuario usuario = Usuario.builder().id(1L).nome("nome").email("jao@jao.com").senha("senha").build();

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
         //acao
         Usuario usuarioSalvo = usuarioService.salvaUsuario(new Usuario());
         //verificacao
         Assertions.assertThat(usuarioSalvo).isNotNull();
         Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1);
         Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
         Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("jao@jao.com");
         Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");

    }

    @Test(expected = Test.None.class)
    public void deveAutenticarUmUsuarioComSucesso(){

        //cenario
        String email = "jao@jao.com";
        String senha = "senha";

        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1L).build();
        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        //acao
        Usuario result = usuarioService.autenticar(email, senha);
        //verificacao

        Assertions.assertThat(result).isNotNull();

    }
    @Test(expected = RegraNegocioException.class)
    public void naoDeveCadastrarUsuarioComEmailCadastrado(){
        //cenario
        String email = "jao@jao.com";
        Usuario usuario = Usuario.builder().email("jao@jao.com").build();
        Mockito.doThrow(RegraNegocioException.class).when(usuarioService).validarEmail(email);
        //acao
        usuarioService.salvaUsuario(usuario);

        //verificacao
        Mockito.verify(usuarioRepository, Mockito.never()).save(usuario);

    }
    @Test
    public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComEmailInformado(){
        //cenario
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
         //acao
         Throwable exception = Assertions.catchThrowable( ()-> usuarioService.autenticar("jao@jao.com", "senha"));
         Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Usuario não encontrado para o email informado");
          //verificacao

    }
    @Test
    public void deveLancarErroQuandoASenhaNaoBater(){
        //cenario
        String senha = "senha";
        Usuario usuario = Usuario.builder().email("jao@jao.com").senha(senha).build();
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
        //acao
        
        //verificacao
        Throwable exception = Assertions.catchThrowable( ()-> usuarioService.autenticar("jao@jao.com", "123456"));
        Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha inválida.");

    }

    @Test(expected =  Test.None.class)
    public void deveValidarEmail(){

        //cenario
       Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        //acao
        usuarioService.validarEmail("jao@jao.com");

    }
    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarQuandoExistirEmailCadastrado(){

        //cenario
        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        //acao
        usuarioService.validarEmail("jao@jao.com");

    }
    
}
