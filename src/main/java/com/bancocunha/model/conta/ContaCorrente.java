/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.model.conta;

import com.bancocunha.model.cliente.Cliente;
import com.bancocunha.model.cliente.ClientePremium;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class ContaCorrente extends Conta {
    private final Double maxEmprestimo;
    public static final Double JUROS_EMPRESTIMO_AM = 0.2;
    private static final Integer MAX_SAQUES = Integer.MAX_VALUE, MAX_TRANS = Integer.MAX_VALUE;
    private static final Double ANUIDADE = 5.00;
    private Double saldoDevedor;
    private final int value;
    public ContaCorrente(Double valorInicial, Cliente cliente) {
        super(valorInicial, MAX_TRANS, MAX_SAQUES, ANUIDADE, cliente);
        if(cliente instanceof ClientePremium ) 
            maxEmprestimo = 2500.00;
        else 
            maxEmprestimo = 200.00;
        this.saldoDevedor = 0.0;
        this.value = 1;
    }
    @Override
    public int getValue() {
        return this.value;
    }
    
    
}
