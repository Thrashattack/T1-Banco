/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancocunha.model.agencia;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class AgenciaOnline extends Agencia{
    
    public AgenciaOnline() {
        super(2222, "", "Agencia Online");
    }
}
