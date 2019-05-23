/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.model.conta;

import com.bancocunha.model.agencia.Agencia;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Carlos Cunha
 */
@Getter
@Setter
public class ContaDTO {

    private int agencia;
    private String conta;
    private String cpfTitular;

    public ContaDTO(Agencia agencia, Conta conta) {
        this.agencia = agencia.getId();
        int tempConta = conta.getId();
        if (tempConta <= 9) {
            this.conta = "000" + tempConta;
        } else if (tempConta > 9 && tempConta <= 99) {
            this.conta = "00" + tempConta;
        } else if (tempConta > 99 && tempConta <= 999) {
            this.conta = "0" + tempConta;
        }

        this.cpfTitular = conta.getTitular().getCpf();
    }

    @Override
    public String toString() {
        return this.agencia + "\n" + this.conta + "\n" + this.cpfTitular;
    }
}
