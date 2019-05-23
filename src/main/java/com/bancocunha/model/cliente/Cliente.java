/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.model.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Carlos Cunha
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Cliente {

    private Double anuidade;
    private String nome;
    private String cpf;
    private String endereco;
    private String dataDeNascimento;

    @Override
    public String toString() {
        return this.nome + "\n" + this.cpf + "\n" + this.dataDeNascimento + "\n";
    }
}
