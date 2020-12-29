package com.crud_java_spring.service;

import com.crud_java_spring.model.Usuario;

public interface UsuarioService {

     Usuario autenticar(String email, String senha);

    Usuario salvaUsuario(Usuario usuario);

    void validarEmail(String email);

    void buscarCep();

   
}
