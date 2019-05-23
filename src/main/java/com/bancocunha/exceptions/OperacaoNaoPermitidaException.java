/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.exceptions;

/**
 *
 * @author Carlos Cunha
 */
public class OperacaoNaoPermitidaException extends RuntimeException {
    public OperacaoNaoPermitidaException() {
       super("Esta Operacao nao e permitida para seu tipo de conta. Por favor entre em contato com a gerencia."); 
    }

}
