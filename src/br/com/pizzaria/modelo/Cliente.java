/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.modelo;

import javax.persistence.*;

/**
 *
 * @author David
 */

@Entity
@Table(name = "pessoa")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Cliente extends Pessoa{
    
    private Boolean cupom;
    
    //private Pedido pedido;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String telefone,Boolean cupom) {
        super(id, nome, email, telefone);
        this.cupom = cupom;
    }

    public Boolean getCupom() {
        return cupom;
    }

    public void setCupom(Boolean cupom) {
        this.cupom = cupom;
    }
    
    
}
