/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.services;

import com.bancocunha.exceptions.LimiteDeOperacoesException;
import com.bancocunha.exceptions.LimiteIndisponivelException;
import com.bancocunha.exceptions.NaoHaDividasException;
import com.bancocunha.exceptions.OperacaoNaoPermitidaException;
import com.bancocunha.exceptions.SaldoDevedorException;
import com.bancocunha.exceptions.SaldoInsuficienteException;
import com.bancocunha.exceptions.SaldoMaximoAtingidoException;
import com.bancocunha.model.conta.Conta;
import com.bancocunha.model.conta.ContaCorrente;
import com.bancocunha.model.conta.ContaFacil;
import com.bancocunha.model.conta.ContaPoupanca;

/**
 *
 * @author Unknow
 */
public class ContaService {
       
    public static void transferir(Conta deConta, Conta paraConta, Double valor) throws RuntimeException {
        if (deConta.getSaldo() < valor)
            throw new SaldoInsuficienteException();
        if (deConta.getTransferenciasNoMes() >= deConta.getLimiteDeTransferencia())
            throw new LimiteDeOperacoesException("transferencias");
                if(paraConta instanceof ContaFacil) {
                    if((paraConta.getSaldo() + valor) > ContaFacil.MAX_VALOR_CARTEIRA)
                        throw new SaldoMaximoAtingidoException();
                    deConta.setSaldo(deConta.getSaldo() - valor);
                    paraConta.setSaldo(paraConta.getSaldo() + valor);                                
                    deConta.getExtrato().push("TRANSFERENCIA " + valor);
                    paraConta.getExtrato().push("RECEBIDO DE TRANSFERENCIA " + valor);                                    
                }
                
        deConta.setSaldo(deConta.getSaldo() - valor);
        paraConta.setSaldo(paraConta.getSaldo() + valor);
        deConta.getExtrato().push("TRANSFERENCIA " + valor);
        paraConta.getExtrato().push("RECEBIDO DE TRANSFERENCIA " + valor);
    }
    
    public static void depositar(Conta conta, Double valor) throws RuntimeException {
        if (conta instanceof ContaFacil) {
            if((conta.getSaldo() + valor) > ContaFacil.MAX_VALOR_CARTEIRA)
                throw new SaldoMaximoAtingidoException();
            conta.setSaldo(conta.getSaldo() + valor);
            conta.getExtrato().push("DEPOSITO " + valor);
        }
        
        conta.setSaldo(conta.getSaldo() + valor);
        conta.getExtrato().push("DEPOSITO " + valor);
    }
    
    public static void sacar(Conta conta, Double valor) throws RuntimeException {
        if (conta instanceof ContaPoupanca)
            throw new OperacaoNaoPermitidaException();
        if (conta.getSaquesNoMes() > conta.getLimiteDeSaque())
            throw new LimiteDeOperacoesException("saques");
        if (conta.getSaldo() < valor)
            throw new SaldoInsuficienteException();        
        conta.setSaldo(conta.getSaldo() - valor);
        conta.getExtrato().push("SAQUE " + valor);       
    }
    
    public static void emprestimo(ContaCorrente conta, Double valor) throws RuntimeException {
        if (!(conta instanceof ContaCorrente))
            throw new OperacaoNaoPermitidaException();
        if (conta.getSaldoDevedor() > 0.0) 
            throw new SaldoDevedorException();
        if (valor > conta.getMaxEmprestimo())
            throw new LimiteIndisponivelException();
        
        conta.setSaldoDevedor(valor);
        conta.getExtrato().push("EMPRESTIMO " + valor);
    }
    
    public static void pagarEmprestimo(ContaCorrente conta, Double valor) throws RuntimeException {
        if (!(conta instanceof ContaCorrente))
            throw new OperacaoNaoPermitidaException();
        if (conta.getSaldoDevedor() <= 0.0) 
            throw new NaoHaDividasException();
        
        conta.setSaldoDevedor(conta.getSaldoDevedor() - valor);
        conta.getExtrato().push("PAGAMENTO EMPRESTIMO " + valor);
    }    
    
    public static String gerarExtrato(Conta conta) {
        return conta.getExtrato().toString();
    }
}
