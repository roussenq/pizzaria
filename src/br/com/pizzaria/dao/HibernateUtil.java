/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Cliente;
import br.com.pizzaria.modelo.Endereco;
import br.com.pizzaria.modelo.Fornecedor;
import br.com.pizzaria.modelo.Pedido;
import br.com.pizzaria.modelo.Pessoa;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author David
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory; //Singleton
    
    static {
        try {
            Configuration cfg = new Configuration(); //tem que colocar na sequencia pai/filho
            cfg.addAnnotatedClass(Pessoa.class);
            cfg.addAnnotatedClass(Endereco.class);
            cfg.addAnnotatedClass(Cliente.class);
            cfg.addAnnotatedClass(Fornecedor.class);
            cfg.addAnnotatedClass(Pedido.class);
        

            cfg.configure("/br/com/pizzaria/dao/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().
                                           applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());
        } catch (HibernateException ex) {
            System.err.println("Erro ao criar Hibernate util." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session abrirConexao() {
        return sessionFactory.openSession();
    }
}
