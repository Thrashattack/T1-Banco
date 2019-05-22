/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.model.cliente;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class ClienteTradicional extends Cliente {
    public static final Double ANUIDADE = 15.00;
    public ClienteTradicional(String nome, String cpf, String data, String endereco) {
        super(ANUIDADE, nome, cpf, endereco, data);
    }
    
}
