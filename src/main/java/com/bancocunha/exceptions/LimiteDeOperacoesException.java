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
public class LimiteDeOperacoesException  extends RuntimeException {
    public LimiteDeOperacoesException(String tipo) {
        super("Limite maximo de " + tipo + " ja foi atingido! impossivel continuar operacao");
    }
}
