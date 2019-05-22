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
public abstract class Conta {
    
    private static int ID = 0001;
    private final int id;
    private Double saldo;
    private final Integer limiteDeTransferencia;
    private final Integer limiteDeSaque;
    private final Double taxa;
    private Integer transferenciasNoMes;
    private Integer saquesNoMes;
    private Cliente titular;
    private Extrato extrato;
    
    
    
    public Conta(Double saldoInicial, Integer limiteT, Integer limiteS, Double taxa, Cliente titular) {
        this.id = Conta.ID++;
        this.saldo = saldoInicial;
        this.limiteDeTransferencia = limiteT;
        this.limiteDeSaque = limiteS;
        this. taxa = taxa;
        this. titular = titular;
        this.extrato = new Extrato(this);
        this.transferenciasNoMes = 0;
        this.saquesNoMes = 0;
    }
    
    public abstract Double getSaldoDevedor();
    public abstract int getValue();
    
}
