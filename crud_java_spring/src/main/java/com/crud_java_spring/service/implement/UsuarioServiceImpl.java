package com.crud_java_spring.service.implement;

import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.transaction.Transactional;

import com.crud_java_spring.exception.ErroAutenticacao;
import com.crud_java_spring.exception.RegraNegocioException;
import com.crud_java_spring.model.Endereco;
import com.crud_java_spring.model.Usuario;
import com.crud_java_spring.repostiory.UsuarioRepository;
import com.crud_java_spring.service.CepService;
import com.crud_java_spring.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Getter
    @Setter
	private Usuario usuario = new Usuario();


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
	private CepService cepService;


   
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if(!usuario.isPresent()){
            throw new ErroAutenticacao("Usuario não encontrado para o email informado");
        }
        if(!usuario.get().getSenha().equals(senha)){
            throw new ErroAutenticacao("Senha inválida.");
        }
        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario salvaUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe =  usuarioRepository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe cadastrado na nossa base de dados");
        } 

    }

    @Override
    public void buscarCep() {
        Endereco endereco = cepService.encontraCEP(usuario.getEndereco().getCep());
		
		if(endereco != null){
			usuario.setEndereco(endereco);
		}else{
			FacesContext.getCurrentInstance().addMessage( null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Servidor não está respondendo", "Servidor não está respondendo"));
		}

    }

      
    
}
