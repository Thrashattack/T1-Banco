/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.exceptions;

/**
 *
 * @author Unknow
 */
public class SaldoDevedorException  extends RuntimeException  {
    public SaldoDevedorException() {
        super("Seu saldo devedor atualmente e positivo, faca o pagamento do emprestimo antes de continuar com a operacao!");
    }
}
