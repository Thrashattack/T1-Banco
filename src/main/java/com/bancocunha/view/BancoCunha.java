/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.view;

import com.bancocunha.controller.BancoController;
import com.bancocunha.exceptions.SaldoInsuficienteException;
import com.bancocunha.model.agencia.Agencia;
import com.bancocunha.model.agencia.AgenciaOnline;
import com.bancocunha.model.cliente.Cliente;
import com.bancocunha.model.conta.Conta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Unknow
 */
public class BancoCunha {
    private static ArrayList<Agencia> agencias;
    private static AgenciaOnline agenciaOnline;
    private static ArrayList<Cliente> clientes;
    private static HashMap<Cliente, ArrayList<Conta>> mapaDeContasPorCliente;
    
    
    public static void main(String [] args) {
         //------------------------------------------|
        BancoCunha.agencias = new ArrayList<>();             //|//
        BancoCunha.clientes = new ArrayList<>();             //|//                   
        BancoCunha.mapaDeContasPorCliente = new HashMap<>(); //|//
        BancoCunha.agenciaOnline = new AgenciaOnline();      //|//
        BancoCunha.agencias.add(agenciaOnline);        //|//
        //----------------------------------------//|//
        
        BancoController controlador = new BancoController(agencias, clientes, mapaDeContasPorCliente, agenciaOnline);
        
        Scanner in =  new Scanner(System.in);
        int evento = in.nextInt();
        in.nextLine();
        String nome, pais, cidade, rua, bairro, cep, numCasa, endereco;
        int idAgencia, idConta;
        Double valor;
        for(;;) {
            switch(evento) {
                case 1:
                    controlador.viradaDoMes();
                    System.out.println("Virada do mes concluida!");
                    break;
                case 2:
                    nome = in.nextLine();
                    int id = in.nextInt();
                    in.nextLine();
                    pais = in.nextLine();
                    cidade = in.nextLine();
                    rua = in.nextLine();
                    bairro = in.nextLine();
                    cep = in.nextLine();
                    numCasa = in.nextLine();
                    endereco = pais + " " + cidade + " " + rua + " " + bairro + " " + cep + " " + numCasa;
                    controlador.cadastrarAgencia(nome, id, endereco);
                    System.out.println("Agencia cadastrada!");
                    break;
                case 3:
                    String tipoConta = in.nextLine();
                    String cpf = in.nextLine();
                    nome = in.nextLine();
                    pais = in.nextLine();
                    cidade = in.nextLine();
                    rua = in.nextLine();
                    bairro = in.nextLine();
                    cep = in.nextLine();
                    numCasa = in.nextLine();
                    endereco = pais + " " + cidade + " " + rua + " " + bairro + " " + cep + " " + numCasa;
                    String data = in.nextLine();
                    String tipoCliente = in.nextLine();
                    int agencia = in.nextInt();
                    in.nextLine();
                    Double valorInicial = in.nextDouble(); 
                    in.nextLine();
                    try {
                       controlador.aberturaDeConta(nome, endereco, tipoConta, cpf, data, tipoCliente, agencia, valorInicial);
                       System.out.println("Conta aberta com sucesso");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:     
                    idAgencia = in.nextInt();
                    idConta = in.nextInt();
                    in.nextLine();
                    valor = in.nextDouble();
                    in.nextLine();                    
                    try {
                       controlador.saque(idAgencia, idConta, valor);                   
                       System.out.println("Saque Realizado. \nValor: " + valor);
                    } catch(SaldoInsuficienteException e) { 
                        System.out.println(e.getMessage());
                            if(e instanceof SaldoInsuficienteException) {
                            Double novoValor = in.nextDouble();
                                if(novoValor == -1.0)
                                    return;
                            controlador.saque(idAgencia, idConta, novoValor);
                            System.out.println("Saque Realizado. \nValor: " + valor);
                        }
                    }

                    break;
                case 5:
                    idAgencia = in.nextInt();
                    idConta = in.nextInt();
                    in.nextLine();
                    valor = in.nextDouble();
                    in.nextLine();
                    try {
                        controlador.depositoEmConta(idAgencia, idConta, valor);
                        System.out.println("Deposito Realizado com Sucesso!");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 6:
                    int idAgenciaDe = in.nextInt();
                    in.nextLine();
                    int idContaDe = in.nextInt();
                    in.nextLine();
                    int idAgenciaPara = in.nextInt();
                    in.nextLine();
                    int idContaPara = in.nextInt();
                    in.nextLine();
                    valor = in.nextDouble();
                    in.nextLine();
                    try {
                        controlador.transferencia(idAgenciaDe, idAgenciaPara, idContaDe, idContaPara, valor);
                        System.out.println("Transferencia Realizada!\nValor: " + valor);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        if(e instanceof SaldoInsuficienteException) {
                            Double novoValor = in.nextDouble();
                            if(novoValor == -1.0)
                                return;
                            controlador.transferencia(idAgenciaDe, idAgenciaPara, idContaDe, idContaPara, novoValor);
                            System.out.println("Transferencia Realizada!\nValor: " + valor);
                        }                    
                    }

                    break;
                case 7:
                    idAgencia = in.nextInt();
                    in.nextLine();
                    idConta = in.nextInt();
                    in.nextLine();
                    valor = in.nextDouble();
                    in.nextLine();
                    try {
                        controlador.solicitarEmprestimo(idAgencia, idConta, valor);
                        System.out.println("Emprestimo Concedido!");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 8:
                    idAgencia = in.nextInt();
                    in.nextLine();
                    idConta = in.nextInt();
                    in.nextLine();
                    try {
                        controlador.gerarExtratos(idAgencia, idConta);
                    } catch(RuntimeException e) {
                        System.out.println(e.getMessage());                }

                    break;
                case 9:                
                    String tipo = in.nextLine();
                    System.out.println(controlador.gerarRelatorios(tipo));
                    break;

            }
            evento = in.nextInt();
            in.nextLine();
        }
    }
        
}

