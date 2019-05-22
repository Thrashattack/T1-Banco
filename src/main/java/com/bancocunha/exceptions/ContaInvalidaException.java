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
public class ContaInvalidaException extends RuntimeException{
    public ContaInvalidaException() {
        super("A conta informada nao foi encontrada, por favor verifique!");
    }
    
}
