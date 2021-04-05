/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author David
 */

@Entity
@Table(name = "fornecedor")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Fornecedor extends Pessoa{
    
    private String cnpj;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String email, String telefone,String cnpj) {
        super(id, nome, email, telefone);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
