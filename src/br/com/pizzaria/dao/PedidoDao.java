/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Pedido;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public interface PedidoDao extends BaseDao<Pedido, Long>{
     
    Pedido pesquisarPorNumeroDoPedido(int numero, Session sessao) throws HibernateException;
}
