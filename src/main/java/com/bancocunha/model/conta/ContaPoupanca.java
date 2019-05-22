/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.model.conta;

import com.bancocunha.model.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class ContaPoupanca extends Conta{
    public static final Double RENDIMENTO_AA = 0.014;
    private Double rendimento;    
    private static final Integer MAX_SAQUES = 0, MAX_TRANS = Integer.MAX_VALUE;
    private static final Double ANUIDADE = 0.00;
    private final int value;
    public ContaPoupanca(Double valorInicial, Cliente cliente) {
        super(valorInicial, MAX_TRANS, MAX_SAQUES, ANUIDADE, cliente);
        this.rendimento = 0.0;
        this.value = 2;
    }
    
    @Override
    public Double getSaldoDevedor() {
        return 0.0;
    }
    @Override
    public int getValue() {
        return this.value;
    }
}
