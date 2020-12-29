package com.crud_java_spring.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    private String cep;// = null;
	private String logradouro, bairro, cidade, numero, complemento;


}