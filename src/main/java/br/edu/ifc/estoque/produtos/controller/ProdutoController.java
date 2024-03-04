package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifc.estoque.produtos.entity.Produto;
import br.edu.ifc.estoque.produtos.bd.BancoDados;

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
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProdutoController {

    @GetMapping("/visualizarProdutos")
    public String visualizarProdutos() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = "";
        String sql = "select * from produto, valores where id=idproduto;";

        try (Connection conn = BancoDados.getConexaoMySQL();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            List<Produto> produtos = new ArrayList<>();

            while (resultSet.next()) {
                Produto produto = new Produto(resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("marca"),
                        resultSet.getInt("tipo"),
                        resultSet.getString("descricao"),
                        resultSet.getString("unidade"),
                        resultSet.getInt("quantidade"),
                        resultSet.getDouble("valor"));

                produtos.add(produto);
            }

            // Converter lista de produtos para JSON
            jsonResult = objectMapper.writeValueAsString(produtos);

        } catch (SQLException | JsonProcessingException e) {
            System.err.println("Erro ao recuperar produtos do banco de dados: " + e.getMessage());
        }

        return jsonResult;
    }

    @PostMapping("/cadastrarProduto")
    public void cadastrarProduto(@RequestBody Produto produto) {
        // Imprimir os dados recebidos na tela
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Marca: " + produto.getMarca());
        System.out.println("Tipo: " + produto.getTipo());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Unidade: " + produto.getUnidade());

        String sql = "INSERT INTO produto (nome, marca, tipo, descricao, unidade, quantidade) VALUES (?, ?, ?, ?, ?, ?)";

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
