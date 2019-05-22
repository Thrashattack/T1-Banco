/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.controller;


import com.bancocunha.exceptions.AgenciaInvalidaException;
import com.bancocunha.exceptions.ContaInvalidaException;
import com.bancocunha.model.agencia.Agencia;
import com.bancocunha.model.agencia.AgenciaOnline;
import com.bancocunha.model.cliente.Cliente;
import com.bancocunha.model.cliente.ClientePremium;
import com.bancocunha.model.cliente.ClienteTradicional;
import com.bancocunha.model.conta.Conta;
import com.bancocunha.model.conta.ContaCorrente;
import com.bancocunha.model.conta.ContaPoupanca;
import com.bancocunha.services.ContaService;
import com.bancocunha.services.RelatoriosService;
import com.bancocunha.services.AgenciaService;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Unknow
 */
public class BancoController {
    private final ArrayList<Agencia> agencias;
    private final ArrayList<Cliente> clientes;
    private final HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorCliente;
    private final AgenciaOnline agenciaOnline;
    private boolean tempStatusDone;
    
    public BancoController(ArrayList<Agencia> agencias, ArrayList<Cliente> clientes,
            HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorClientes, AgenciaOnline agenciaOnline) {
        this.agencias = agencias;
        this.clientes = clientes;
        this.mapaDeContasPorCliente = mapaDeContasPorClientes;
        this.agenciaOnline = agenciaOnline;
        
    }
    public void cadastrarAgencia(String nome, int id, String endereco) {
        this.agencias.add(new Agencia(id, endereco, nome));        
    }
    
    public void viradaDoMes() {
        this.agencias.forEach(agencia -> {
            agencia.getContas().forEach(conta -> {
                if(conta instanceof ContaPoupanca)
                    AgenciaService.renderPoupanca((ContaPoupanca) conta);
                if(conta instanceof ContaCorrente)
                    AgenciaService.atualizarDivida((ContaCorrente) conta);
            });
        });
    }
    
    public void aberturaDeConta(String nome, String endereco, String tipoConta,
            String cpf, String data, String tipoCliente, int agencia, Double valorInicial) throws RuntimeException {
        tempStatusDone = false;
        this.clientes.forEach( cliente -> {
            if(cliente.getCpf().equals(cpf)) {
                cliente.setNome(nome);
                cliente.setEndereco(endereco);
                cliente.setDataDeNascimento(data);
                if (tipoCliente.equals("TRADICIONAL"))
                     cliente.setAnuidade(ClienteTradicional.ANUIDADE);
                else if (tipoCliente.equals("PREMIUM"))
                     cliente.setAnuidade(ClientePremium.ANUIDADE);                
                if(tipoConta.equals("F")) {                    
                    this.mapaDeContasPorCliente.get(cliente).add(AgenciaService.abrirConta(this.agenciaOnline, cliente, valorInicial, tipoConta)
                    );
                    return;
                }
                this.agencias.forEach(agenciaAtual -> {
                    System.out.println("Agencia atual:" + agenciaAtual.getId());
                    System.out.println("Agencia recebida:" + agencia);
                    if(agenciaAtual.getId() == agencia) {
                        Conta conta = AgenciaService.abrirConta(agenciaAtual, cliente, valorInicial, tipoConta);
                        this.mapaDeContasPorCliente.get(cliente).add(conta);  
                        tempStatusDone = true;
                    }
                }); // Agencia nao encontrada
                if(!tempStatusDone)
                   throw new AgenciaInvalidaException();
            }                
        }); // cpf nao encontrado
        Cliente cliente;
        if(tipoCliente.equals("PREMIUM")) 
            cliente = new ClientePremium(nome, cpf, data, endereco);
        else
            cliente = new ClienteTradicional(nome, cpf, data, endereco);
            
        this.mapaDeContasPorCliente.put(cliente, new ArrayList<>());
        if (tipoConta.equals("F")) {
            this.mapaDeContasPorCliente.get(cliente).add(AgenciaService.abrirConta(this.agenciaOnline, cliente, valorInicial, tipoConta)
            );
            return;
        }
        this.agencias.forEach(agenciaAtual -> {
            if(agenciaAtual.getId() == agencia) {
                Conta conta = AgenciaService.abrirConta(agenciaAtual, cliente, valorInicial, tipoConta);
                this.mapaDeContasPorCliente.get(cliente).add(conta);
                tempStatusDone = true;
            }
        }); // Agencia nao encontrada
        if(!tempStatusDone)
           throw new AgenciaInvalidaException();
        
    }
    
    public void saque(int idAgencia, int idConta, Double valor) throws RuntimeException {   
        tempStatusDone = false;
        this.agencias.forEach(agencia -> {
            if(agencia.getId() == idAgencia) {
                agencia.getContas().forEach(conta -> {
                    if(conta.getId() == idConta) {                         
                        ContaService.sacar(conta, valor);  
                        tempStatusDone = true;
                    }
                });                
                if(!tempStatusDone)throw new ContaInvalidaException();  
            }         
        });
        if(!tempStatusDone) throw new AgenciaInvalidaException();
    }
    
    public void depositoEmConta(int idAgencia, int idConta, Double valor) throws RuntimeException {     
        tempStatusDone = false;
        this.agencias.forEach(agencia -> {
            if(agencia.getId() == idAgencia) {
                agencia.getContas().forEach(conta -> {
                    if(conta.getId() == idConta){                       
                        ContaService.depositar(conta, valor);   
                        tempStatusDone = true;
                    }
                });
                if(!tempStatusDone) throw new ContaInvalidaException();
            }           
        });
        if(!tempStatusDone) throw new AgenciaInvalidaException();
    }
    
    public void transferencia(int idAgenciaDe, int idAgenciaPara,
            int idContaDe, int idContaPara, Double valor) throws RuntimeException {        
        tempStatusDone = false;
        this.agencias.forEach(agenciaDe -> {
            if(agenciaDe.getId() == idAgenciaDe) {
                agenciaDe.getContas().forEach(contaDe -> {
                    if(contaDe.getId() == idContaDe) {
                        this.agencias.forEach(agenciaPara -> {
                            if(agenciaPara.getId() == idAgenciaPara) {
                                agenciaPara.getContas().forEach(contaPara -> {
                                    if(contaPara.getId() == idContaPara) {                             
                                        ContaService.transferir(contaDe, contaPara, valor);
                                        tempStatusDone = true;
                                    }
                                });
                                if(!tempStatusDone) throw new RuntimeException("Conta de Destino Invalida\n");                        
                            }
                        });
                        if(!tempStatusDone) throw new RuntimeException("Agencia de destino invalida!\n");
                    }
                });  
               if(!tempStatusDone) throw new RuntimeException("Conta de origem invalida!\n");
            }
        }); 
       if(!tempStatusDone) throw new RuntimeException("Agencia de origem invalida!\n");        
        
    }
    
    public void solicitarEmprestimo(int idAgencia, int idConta, Double valor) throws RuntimeException { 
        tempStatusDone = false;
        this.agencias.forEach(agencia -> {
            if(agencia.getId() == idAgencia) {
                agencia.getContas().forEach(conta -> {
                    if(conta.getId() == idConta) { 
                       ContaService.emprestimo((ContaCorrente) conta, valor);
                       tempStatusDone = true;
                    }
                });
            if(!tempStatusDone) throw new ContaInvalidaException();
            }            
        });
        if(!tempStatusDone) throw new AgenciaInvalidaException();
    }
    
    public void gerarExtratos(int idAgencia, int idConta) throws RuntimeException {
        tempStatusDone = false;
        this.agencias.forEach(agencia -> {
            if(agencia.getId()  == idAgencia) {
                agencia.getContas().forEach(conta -> {
                    if(conta.getId() == idConta) { 
                        System.out.println(ContaService.gerarExtrato(conta));
                        tempStatusDone = true;
                    }
                });
            if(!tempStatusDone) throw new ContaInvalidaException();    
            }            
        });
        if(!tempStatusDone) throw new AgenciaInvalidaException();
        
    }
    
    public String gerarRelatorios(String tipo) {
        switch(tipo) {            
            case "A":
                return RelatoriosService.listar_por_agencia_e_numero_da_conta(this.agencias);                
            case "B":   
                return RelatoriosService.listar_por_nome_do_cliente_e_tipo_da_conta(this.mapaDeContasPorCliente, this.agencias);
            case "C":
                return RelatoriosService.listar_poupancas(this.agencias);
            case "D":
                return RelatoriosService.listar_correntes(this.agencias);
            case "E":
                return RelatoriosService.listar_faceis(this.agencias);
            case "F":
                return RelatoriosService.listar_clientes_com_varias_contas(this.mapaDeContasPorCliente);
            case "G":
                return RelatoriosService.listar_clientes(this.mapaDeContasPorCliente);
            default:
                return "Tipo Incorreto";                
        }        
        
    }
}
