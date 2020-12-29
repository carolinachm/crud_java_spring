package com.crud_java_spring.DTO;

import com.crud_java_spring.model.Endereco;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@Builder
public class UsuarioDTO {

    private String email;
    private String nome;
    private String senha;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    
}
