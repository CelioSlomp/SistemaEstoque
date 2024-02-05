package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifc.estoque.produtos.entity.Produto;
import br.edu.ifc.estoque.produtos.bd.BancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProdutoController {
    @GetMapping("/listaProdutos")
    public String listaProdutos(){
        return "lista_produtos";
    }

    @PostMapping("/cadastrarProduto")
    public void cadastrarProduto(@RequestBody Produto produto) {
        // Imprimir os dados recebidos na tela
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Marca: " + produto.getMarca());
        System.out.println("Tipo: " + produto.getTipo());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Unidade: " + produto.getUnidade());

        String sql = "INSERT INTO produto (nome, marca, tipo, descricao, unidade, quantidade, vlrPago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            Connection conn = BancoDados.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            // Atribuir valores aos parâmetros
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getMarca());
            statement.setInt(3, produto.getTipo());
            statement.setString(4, produto.getDescricao());
            statement.setString(5, produto.getUnidade());
            statement.setInt(6, 0);
            statement.setDouble(7, 0);
            
            // executar o sql
            int linhasAfetadas = statement.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("Produto Inserido!");
            } else {
                System.out.println("Produto não Inserido");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
        }

        
        
    
    }

    
}
