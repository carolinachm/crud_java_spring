package com.crud_java_spring.DTO;

import com.crud_java_spring.model.Endereco;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class UsuarioDTO {

    private String email;
    private String nome;
    private String senha;
    private String cpf;
    private String telefone;
    private Endereco endereco;


    public UsuarioDTO() {
    }

    public UsuarioDTO(String email, String nome, String senha, String cpf, String telefone, Endereco endereco) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    
}

  