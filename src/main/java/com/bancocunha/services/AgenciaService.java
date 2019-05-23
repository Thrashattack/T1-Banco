/*
 * Controlador de operações do BancoCunha 
 * Implementa todas as operações descritas nos requisitos do trabalho.
 * 
 */
package com.bancocunha.services;

import com.bancocunha.model.agencia.Agencia;
import com.bancocunha.model.cliente.Cliente;
import com.bancocunha.model.conta.*;

/**
 *
 * @author Carlos Cunha
 */
public class AgenciaService {

    public static void renderPoupanca(ContaPoupanca poupanca) {
        Double rendimento = poupanca.getRendimento() + (poupanca.getSaldo() * (ContaPoupanca.RENDIMENTO_AA / 12.00));
        poupanca.setRendimento(rendimento);
        poupanca.getExtrato().push("RENDIMENTO " + rendimento);
    }

    public static void atualizarDivida(ContaCorrente corrente) {
        corrente.setSaldoDevedor(corrente.getSaldoDevedor() + (corrente.getSaldoDevedor() * ContaCorrente.JUROS_EMPRESTIMO_AM));
    }

    public static Conta abrirConta(Agencia agencia, Cliente cliente, Double valorInicial, String tipo) {
        Conta novaConta;
        switch (tipo) {
            case "C":
                novaConta = new ContaCorrente(valorInicial, cliente);
                agencia.getContas().add(novaConta);
                return novaConta;
            case "P":
                novaConta = new ContaPoupanca(valorInicial, cliente);
                agencia.getContas().add(novaConta);
                return novaConta;
            case "F":
            default:
                novaConta = new ContaFacil(valorInicial, cliente);
                agencia.getContas().add(novaConta);
                return novaConta;
        }

    }

    public static void fecharConta(Agencia agencia, Conta conta) {
        agencia.getContas().remove(conta);
    }

}
