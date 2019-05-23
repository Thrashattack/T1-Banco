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
public class LimiteIndisponivelException extends RuntimeException {
    public LimiteIndisponivelException() {
        super("Nao ha limite disponivel, tente um valor menor!");
    }
}
