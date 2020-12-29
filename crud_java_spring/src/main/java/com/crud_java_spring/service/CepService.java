package com.crud_java_spring.service;

import javax.inject.Named;

import com.crud_java_spring.model.Endereco;



@Named
public class CepService {


    public Endereco encontraCEP(String cep) {
        
        CepWebService cepWebService = new CepWebService(cep);
        
        Endereco endereco = new Endereco();
        

        if (cepWebService.getResultado() == 1) {
            //setTipoLogradouro(cepWebService.getTipoLogradouro());
            endereco.setLogradouro(cepWebService.getLogradouro());
            endereco.setComplemento(cepWebService.getComplemento());
            endereco.setCidade(cepWebService.getCidade());
            endereco.setBairro(cepWebService.getBairro());
          return endereco;
        } else {
            return null;
        }
    }


}
