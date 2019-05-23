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
 * @author Carlos Cunha
 */
@Getter
@Setter
public class ClientePremium extends Cliente {

    public static final Double ANUIDADE = 0.0;

    public ClientePremium(String nome, String cpf, String data, String endereco) {
        super(ANUIDADE, nome, cpf, endereco, data);
    }
}
