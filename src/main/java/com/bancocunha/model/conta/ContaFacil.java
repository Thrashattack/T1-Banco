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
public class ContaFacil extends Conta{
    public static final Double MAX_VALOR_CARTEIRA = 5000.00;
    private static final Integer MAX_SAQUES = 1, MAX_TRANS = 1;
    private static final Double ANUIDADE = 10.00;
    private final int value;
    public ContaFacil(Double valorInicial, Cliente cliente) {
        super(valorInicial, MAX_SAQUES, MAX_TRANS, ANUIDADE, cliente);
        this.value = 0;
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
