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
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
       
    }

    @Test
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
    
    private Endereco gerarEndereco(){
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
      
    }
    //@Test
    public void testAlterarCliente() {
        System.out.println("Alterar Cliente");
      
    }
    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
      
    }

    //@Test
    public void testPesquisarClienteComEndereco() {
        System.out.println("pesquisarClienteComEndereco");
      
    }
    
}
