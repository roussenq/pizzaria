/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Fornecedor;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


public class FornecedorDaoImpl extends BaseDaoImpl<Fornecedor, Long> implements FornecedorDao, Serializable {

    @Override
    public Fornecedor pesquisarFornecedorComEndereco(Long id, Session sessao) throws HibernateException {
         Query consulta = sessao.createQuery("from Fornecedor f join fetch f.enderecos where f.id = :id");
        consulta.setParameter("id", id);
        return (Fornecedor) consulta.uniqueResult();
    }

    @Override
    public List<Fornecedor> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
         Query consulta = sessao.createQuery("from Fornecedor f join fetch f.enderecos where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public Fornecedor pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Fornecedor)sessao.get(Fornecedor.class, id);
    }
    
}
 
     
