/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Cliente;
import br.com.pizzaria.modelo.Endereco;
import static br.com.utilitario.UtilGerador.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class ClienteDaoImplTest {

    private Cliente cliente;
    private ClienteDao clienteDao;
    private Session sessao;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

    //@Test
    public void testSalvarCliente() {
        System.out.println("Salvar Cliente");

        List<Endereco> enderecos = new ArrayList<>();

        cliente = new Cliente(
                null,
                gerarNome(),
                gerarEmail(),
                gerarTelefoneFixo(),
                true
        );

        enderecos.add(gerarEndereco());
        enderecos.add(gerarEndereco());

        cliente.setEnderecos(enderecos);

        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();

        assertNotNull(cliente.getId());
        assertNotNull(cliente.getEnderecos().get(0).getId());
    }

    //@Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        
        buscarClienteBd();
        String nome = cliente.getNome();
        int letra = nome.indexOf(" ");
        nome = nome.substring(0, letra);
        
        sessao = HibernateUtil.abrirConexao();
        List<Cliente> clientes = clienteDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        
        assertTrue(!clientes.isEmpty());
    }

    private Endereco gerarEndereco() {
        Endereco endereco = new Endereco(
                null,
                gerarNome(),
                gerarNumero(8),
                gerarNumero(3),
                "Centro",
                gerarCidade(),
                "casa da frente",
                "bater palma"
        );
        endereco.setPessoa(cliente);
        return endereco;
    }

    //@Test
    public void testExcluirCliente() {
        System.out.println("Excluir Cliente");

        buscarClienteBd();
        System.out.println("será excluido\nNome: " + cliente.getNome() + "\nId:" + cliente.getId());
        sessao = HibernateUtil.abrirConexao();
        clienteDao.excluir(cliente, sessao);
        sessao.close();
        sessao = HibernateUtil.abrirConexao();
        Cliente clienteExcluido = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();

        assertNull(clienteExcluido);

    }

    //@Test
    public void testAlterarCliente() {
        System.out.println("Alterar Cliente");
        buscarClienteBd();
        
        cliente.setNome("David Roussenq Maria");
        Endereco end1 = cliente.getEnderecos().get(0);
        Endereco end2 = cliente.getEnderecos().get(1);
        
        end1.setCidade("Florianopolis");
        end2.setCidade("Palhoca");

        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        Cliente clienteAlt = clienteDao.pesquisarClienteComEndereco(cliente.getId(), sessao);
        sessao.close();

        assertEquals(cliente.getNome(), clienteAlt.getNome());
        assertEquals(cliente.getEnderecos().get(0).getCidade(), clienteAlt.getEnderecos().get(0).getCidade());
        assertEquals(cliente.getEnderecos().get(1).getCidade(), clienteAlt.getEnderecos().get(1).getCidade());
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisar Por Id Com Endereço");
        buscarClienteBd();

        sessao = HibernateUtil.abrirConexao();
        Cliente clientePesquisado = clienteDao.pesquisarClienteComEndereco(cliente.getId(), sessao);
        sessao.close();

        assertNotNull(clientePesquisado);
    }

    //@Test
    public void testPesquisarClienteComEndereco() {
        System.out.println("pesquisarClienteComEndereco");

    }

    //@Test
    public void testPesquisarPorTelefone() {
        System.out.println("pesquisarPorTelefone");
        buscarClienteBd();
        
        sessao = HibernateUtil.abrirConexao();
        String telefone = cliente.getTelefone();
        Cliente clienteTel = clienteDao.pesquisarPorTelefone(telefone, sessao);
        sessao.close();
        
        assertNotNull(clienteTel);
        assertNotNull(clienteTel.getEnderecos().get(0));
    }
    
    public Cliente buscarClienteBd() {

        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Cliente c join fetch c.enderecos ");
        List<Cliente> clientes = consulta.list();
        sessao.close();

        if (clientes.isEmpty()) {
            testSalvarCliente();
        } else {
            cliente = clientes.get(0);
        }
        return cliente;
    }

    

}
