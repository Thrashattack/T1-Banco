/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.services;

import com.bancocunha.model.agencia.Agencia;
import com.bancocunha.model.cliente.Cliente;
import com.bancocunha.model.conta.Conta;
import com.bancocunha.model.conta.ContaCorrente;
import com.bancocunha.model.conta.ContaDTO;
import com.bancocunha.model.conta.ContaFacil;
import com.bancocunha.model.conta.ContaPoupanca;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Carlos Cunha
 */
public class RelatoriosService {

    private static String resultado;

    public static String listar_por_agencia_e_numero_da_conta(ArrayList<Agencia> agencias) {
        resultado = "";
        //Ordena pelo id da agencia
        Collections.sort(agencias, (Object AgenciaA, Object AgenciaB) -> { 
            Agencia agencia1 = (Agencia) AgenciaA;
            Agencia agencia2 = (Agencia) AgenciaB;
            if (agencia1.getId() == agencia2.getId()) {
                return 0;
            }
            return agencia1.getId() < agencia2.getId() ? -1 : 1;
        });
        //Ordena contas pelo id da conta
        agencias.forEach(agencia -> {
            Collections.sort(agencia.getContas(), (Object ContaA, Object ContaB) -> {
                Conta conta1 = (Conta) ContaA;
                Conta conta2 = (Conta) ContaB;
                if (conta1.getId() == conta2.getId()) {
                    return 0;
                }
                return conta1.getId() < conta2.getId() ? -1 : 1;
            });
        });
        agencias.forEach(agencia -> {
            resultado += agencia.getContas()
                    .stream()
                    .map((conta) -> new ContaDTO(agencia, conta).toString() + "\n")
                    .reduce(resultado, String::concat); // reduz atraves de concat para o resultado
        });
        return resultado;
    }

    public static String listar_por_nome_do_cliente_e_tipo_da_conta(HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorCliente,
            ArrayList<Agencia> agencias) {
        resultado = "";
        //Ordena os clientes pelo nome
        Arrays.sort(mapaDeContasPorCliente.keySet().toArray(), (Object clienteA, Object clienteB) -> {
            Cliente cliente1 = (Cliente) clienteA;
            Cliente cliente2 = (Cliente) clienteB;
            return cliente1.getNome().compareTo(cliente2.getNome());
        });
        //Ordena quando houver mais de uma conta por cliente pelo tipo da conta (value)
        for (ArrayList<Conta> contasDoCliente : mapaDeContasPorCliente.values()) {
            if (contasDoCliente.size() > 1) {
                Collections.sort(contasDoCliente, (Object contaA, Object contaB) -> {
                    Conta conta1 = (Conta) contaA;
                    Conta conta2 = (Conta) contaB;
                    if (conta1.getValue() == conta2.getValue()) {
                        return 0;
                    }
                    return conta1.getValue() < conta2.getValue() ? -1 : 1;
                });
            }
        }
        agencias.forEach(agencia -> {
             resultado += agencia.getContas()
                    .stream() 
                    .map((conta) -> new ContaDTO(agencia, conta).toString() + "\n") 
                    .reduce(resultado, String::concat); 
        });
        return resultado;
    }

    public static String listar_poupancas(ArrayList<Agencia> agencias) {
        resultado = "";
        agencias.forEach(agencia -> {
             resultado += agencia.getContas()
                    .stream()
                    .filter(conta -> conta instanceof ContaPoupanca)
                    .map(conta -> new ContaDTO(agencia, conta).toString() + "\n")
                    .reduce(resultado, String::concat);
        });
        return resultado;
    }

    public static String listar_correntes(ArrayList<Agencia> agencias) {
        resultado = "";
        agencias.forEach(agencia -> {
             resultado += agencia.getContas()
                    .stream()
                    .filter(conta -> conta instanceof ContaCorrente)
                    .map(conta -> new ContaDTO(agencia, conta).toString() + "\n")
                    .reduce(resultado, String::concat);
        });
        return resultado;
    }

    public static String listar_faceis(ArrayList<Agencia> agencias) {
        resultado = "";
        agencias.forEach(agencia -> {
             resultado += agencia.getContas()
                    .stream()
                    .filter(conta -> conta instanceof ContaFacil)
                    .map(conta -> new ContaDTO(agencia, conta).toString() + "\n")
                    .reduce(resultado, String::concat);
        });
        return resultado;
    }

    public static String listar_clientes_com_varias_contas(HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorCliente) {
        resultado = "";
         resultado += mapaDeContasPorCliente.entrySet()
                .stream()
                .filter(node -> node.getValue().size() > 1)
                .map(node -> node.getKey().toString() + "\n")
                .reduce(resultado, String::concat);
        return resultado;
    }

    public static String listar_clientes(HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorCliente) {
        resultado = "";
        //Ordena por nome do cliente
        Arrays.sort(mapaDeContasPorCliente.keySet().toArray(), (Object clienteA, Object clienteB) -> {
            Cliente cliente1 = (Cliente) clienteA;
            Cliente cliente2 = (Cliente) clienteB;
            return cliente1.getNome().compareTo(cliente2.getNome());
        });
         resultado += mapaDeContasPorCliente.entrySet()
                .stream()
                .map(node -> node.getKey().toString())
                .reduce(resultado, String::concat);        
        return resultado;
    }

}
