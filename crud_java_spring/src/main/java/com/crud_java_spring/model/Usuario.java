package com.crud_java_spring.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Entity
@Data
public class Usuario {
	
      @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
      private Long id;
      private String nome;
      private String senha;
      private String cpf;
      private String telefone;
      @JoinColumn
      @ManyToOne
      private Perfil perfil;
     
      
     

	  
	 
    

}
