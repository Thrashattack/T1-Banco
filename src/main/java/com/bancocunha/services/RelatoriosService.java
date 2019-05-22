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
import java.util.List;

/**
 *
 * @author Unknow
 */
public class RelatoriosService {
    private static String resultado;
    public static String listar_por_agencia_e_numero_da_conta(ArrayList<Agencia> agencias) {
        resultado = "";
        Collections.sort(agencias, (Object AgenciaA, Object AgenciaB) -> { // implementa o metodo compare comparando pelo id
            Agencia agencia1 = (Agencia) AgenciaA;
            Agencia agencia2 = (Agencia) AgenciaB;
            if (agencia1.getId() == agencia2.getId()) {
                return 0;
            }
            return agencia1.getId() < agencia2.getId() ? -1 : 1;
        });
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
            agencia.getContas().forEach(conta -> {
                resultado += (new ContaDTO(agencia, conta).toString() + "\n");
            });
        });
        return resultado;
    }

    public static String listar_por_nome_do_cliente_e_tipo_da_conta(HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorCliente,
            ArrayList<Agencia> agencias) {
        resultado = "";
        Arrays.sort(mapaDeContasPorCliente.keySet().toArray(), (Object clienteA, Object clienteB) -> {
            Cliente cliente1 = (Cliente) clienteA;
            Cliente cliente2 = (Cliente) clienteB;
            return cliente1.getNome().compareTo(cliente2.getNome());
        });
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
            agencia.getContas().forEach(conta -> {
                resultado += (new ContaDTO(agencia, conta).toString() + "\n");
            });
        });
        return resultado;
    }

    public static String listar_poupancas(ArrayList<Agencia> agencias) {
        resultado = "";
        agencias.forEach(agencia -> {
            agencia.getContas().forEach(conta -> {
                if (conta instanceof ContaPoupanca) {
                    resultado += (new ContaDTO(agencia, conta).toString() + "\n");
                }
            });
        });
        return resultado;
    }

    public static String listar_correntes(ArrayList<Agencia> agencias) {
        resultado = "";
        agencias.forEach(agencia -> {
            agencia.getContas().forEach(conta -> {
                if (conta instanceof ContaCorrente) {
                    resultado += (new ContaDTO(agencia, conta).toString() + "\n");
                }
            });
        });
        return resultado;
    }

    public static String listar_faceis(ArrayList<Agencia> agencias) {
        resultado = "";
        agencias.forEach(agencia -> {
            agencia.getContas().forEach(conta -> {
                if (conta instanceof ContaFacil) {
                    resultado += (new ContaDTO(agencia, conta).toString() + "\n");
                }
            });
        });
        return resultado;
    }

    public static String listar_clientes_com_varias_contas(HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorCliente) {
        resultado = "";
        mapaDeContasPorCliente.entrySet().forEach((node) -> {
            List listaDeContasDoCliente = (List) node.getValue();
            if (listaDeContasDoCliente.size() > 1) {
                resultado += (node.getKey().toString() + "\n");
            }
        });
        return resultado;
    }

    public static String listar_clientes(HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorCliente) {
        resultado = "";
        Arrays.sort(mapaDeContasPorCliente.keySet().toArray(), (Object clienteA, Object clienteB) -> {
            Cliente cliente1 = (Cliente) clienteA;
            Cliente cliente2 = (Cliente) clienteB;
            return cliente1.getNome().compareTo(cliente2.getNome());
        });
        mapaDeContasPorCliente.entrySet().forEach(node -> {
            resultado += (node.getKey().toString());
        });
        return resultado;
    }

}
