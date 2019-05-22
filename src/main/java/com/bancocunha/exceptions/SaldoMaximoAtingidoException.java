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
public class SaldoMaximoAtingidoException  extends RuntimeException {
    public SaldoMaximoAtingidoException () {
        super("A conta atingiu o limite maximo de valores, por favor retire um pouco antes de tentar depositar ou transferir novamente.");
    }
}
