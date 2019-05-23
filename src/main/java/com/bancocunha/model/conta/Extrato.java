/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.model.conta;

import java.util.Stack;

/**
 *
 * @author Carlos Cunha
 */
public class Extrato {

    private final Stack<String> cacheDeOp;
    private final Conta conta;

    public Extrato(Conta conta) {
        this.cacheDeOp = new Stack<>();
        this.conta = conta;
    }

    public void push(String operacao) {
        if (this.cacheDeOp.size() == 10) {
            this.cacheDeOp.remove(this.cacheDeOp.lastElement());
        }
        this.cacheDeOp.push(operacao);
    }

    @Override
    public String toString() {
        String result = "";
        this.cacheDeOp.stream()
                .map((operacao) -> operacao + "\n")
                .reduce(result, String::concat);
        String saida = "#Saida\n" + result + "\nSALDO ATUAL " + conta.getSaldo();
        if (conta instanceof ContaCorrente) 
            saida += "\nSALDO DEVEDOR " + conta.getSaldoDevedor();
        
        return saida;
    }
}
