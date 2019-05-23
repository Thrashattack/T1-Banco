/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.model.agencia;

import com.bancocunha.model.conta.Conta;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Carlos Cunha
 */
@Getter
@Setter
public class Agencia {

    private String endereco;
    private String nome;
    private final int id;
    private final List<Conta> contas;

    public Agencia(int id, String addr, String nome) {
        this.endereco = addr;
        this.id = id;
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

}
