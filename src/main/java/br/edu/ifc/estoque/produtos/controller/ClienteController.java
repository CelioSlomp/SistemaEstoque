package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifc.estoque.produtos.bd.BancoDados;

import br.edu.ifc.estoque.produtos.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ClienteController {

    @GetMapping("/visualizarClientes")
    public String visualizarClientes(){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = "";
        String sql = "SELECT * FROM cliente";
        
        try (Connection conn = BancoDados.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            
            List<Cliente> clientes = new ArrayList<>();
            
            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt("id"), 
                                              resultSet.getString("nome"), 
                                              resultSet.getInt("bomPag"));
                
                clientes.add(cliente);
            }
            
            // Converter lista de clientes para JSON
            jsonResult = objectMapper.writeValueAsString(clientes);
        } catch (SQLException | JsonProcessingException e) {
            System.err.println("Erro ao recuperar produtos do banco de dados: " + e.getMessage());
        }

        return jsonResult;
    }

    @PostMapping("/cadastrarCliente")
    public void cadastrarCliente(@RequestBody Cliente cliente){
        // Imprimir os dados recebidos na tela
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("bomPag: " + cliente.getBomPag());

        String sql = "INSERT INTO cliente (nome, bomPag) VALUES (?, ?)";
        
        try {
            Connection conn = BancoDados.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            // Atribuir valores aos parâmetros
            statement.setString(1, cliente.getNome());
            statement.setInt(2, cliente.getBomPag());
            
            // executar o sql
            int linhasAfetadas = statement.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("Cliente Inserido!");
            } else {
                System.out.println("Cliente não Inserido");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
        }
    
    }
}
