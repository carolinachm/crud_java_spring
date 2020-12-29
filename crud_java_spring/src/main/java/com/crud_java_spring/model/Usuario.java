package com.crud_java_spring.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="usuario")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
      @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
      private Long id;
      @Column(name="nome")
      private String nome;
      @Column(name="senha")
      private String senha;
      @Column(name="cpf")
      private String cpf;
      @Column(name="telefone")
      private String telefone;
      @Column(name="email")
      private String email;
      @Embedded
	private Endereco endereco = new Endereco();


 

}
