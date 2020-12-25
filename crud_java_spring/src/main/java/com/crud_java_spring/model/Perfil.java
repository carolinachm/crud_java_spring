package com.crud_java_spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Perfil {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String descricao;
    
}
