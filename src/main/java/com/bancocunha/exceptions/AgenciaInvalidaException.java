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
public class AgenciaInvalidaException extends RuntimeException {
    public AgenciaInvalidaException() {
        super("A agencia informada nao e valida! Por favor verifique!");
    }
    
}
