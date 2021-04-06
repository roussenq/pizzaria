/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Cliente;
import br.com.pizzaria.modelo.Pedido;
import br.com.utilitario.UtilGerador;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class PedidoDaoImplTest {
    
    private Pedido pedido;
    private int numeroPedido;
    private PedidoDao pedidoDao;
    private Session sessao;
    
    public PedidoDaoImplTest() {
        pedidoDao = new PedidoDaoImpl();
    }

    //@Test
    public void testSalvarPedido() {
        System.out.println("Salvar Pedido");
        ClienteDaoImplTest clienteDaoImplTest = new ClienteDaoImplTest();
        Cliente cliente = clienteDaoImplTest.buscarClienteBd();
        gerarNumeroPedido();
        pedido = new Pedido(
                null,
                numeroPedido,
                new BigDecimal(UtilGerador.gerarNumero(3)),
                new Date()
        );
        pedido.setCliente(cliente);
        sessao = HibernateUtil.abrirConexao();
        pedidoDao.salvarOuAlterar(pedido, sessao);
        sessao.close();
        
        assertNotNull(pedido.getId());
    }
   
    @Test
    public void testAlterarPedido() {
        System.out.println("Alterar Pedido");
        buscarPedidoBd();
        
        pedido.setValorTotal(BigDecimal.valueOf(5.65));
        
        sessao = HibernateUtil.abrirConexao();
        pedidoDao.salvarOuAlterar(pedido, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Pedido pedidoAlt = pedidoDao.pesquisarPorId(pedido.getId(), sessao);
        sessao.close();
        
        assertEquals(pedido.getValorTotal(),pedidoAlt.getValorTotal());
    }
    @Test
    public void testPesquisarPorNumeroDoPedido() {
        System.out.println("pesquisarPorNumeroDoPedido");
        buscarPedidoBd();
        sessao = HibernateUtil.abrirConexao();
        Pedido pedidoBd = pedidoDao.pesquisarPorNumeroDoPedido(pedido.getNumero(), sessao);
        sessao.close();
        
        assertNotNull(pedidoBd);
        assertNotNull(pedidoBd.getCliente().getEnderecos().get(0));
    }

    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
    }
    
    public Pedido buscarPedidoBd(){
        List<Pedido> pedidos = pesquisarPedidoBd2();

        if (pedidos.isEmpty()) {
            testSalvarPedido();
        } else {
            pedido = pedidos.get(0);
        }
        return pedido;
    }

    private List<Pedido> pesquisarPedidoBd2() throws HibernateException {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Pedido");
        List<Pedido> pedidos = consulta.list();
        sessao.close();
        return pedidos;
    }
    
    private void gerarNumeroPedido(){
         List<Pedido> pedidos = pesquisarPedidoBd2();
         if (pedidos.isEmpty()) {
            numeroPedido = 1;
        } else {
            int tamanhoPedido = pedidos.size();
            numeroPedido = tamanhoPedido + 1;
        }
        
        
    }
    
}
