package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

import br.edu.ifc.estoque.produtos.bd.BancoDados;
import br.edu.ifc.estoque.produtos.entity.Compra;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CompraController {
    @GetMapping("/listaCompras")
    public String lista_compras() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = "";
        String sql = "SELECT * FROM compra";

        try (Connection conn = BancoDados.getConexaoMySQL();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            List<Compra> compras = new ArrayList<>();

            while (resultSet.next()) {
                Compra compra = new Compra(resultSet.getInt("idProduto"),
                        resultSet.getInt("quantidade"),
                        resultSet.getDouble("vlrPago"));

                compras.add(compra);
            }

            // Converter lista de clientes para JSON
            jsonResult = objectMapper.writeValueAsString(compras);
        } catch (SQLException | JsonProcessingException e) {
            System.err.println("Erro ao recuperar produtos do banco de dados: " + e.getMessage());
        }

        return jsonResult;
    }

    @PostMapping("/realizarCompra")
    public void realizarCompra(@RequestBody Compra compra) {
        // Imprimir os dados recebidos na tela
        System.out.println("id: " + compra.getIdProduto());
        System.out.println("quantidade: " + compra.getQuantidade());
        System.out.println("valor: " + compra.getVlrPago());

        String sqlSelect = "SELECT quantidade FROM valores WHERE idProduto = ? AND valor = ?";
        String sqlInsert = "INSERT INTO valores (idProduto, quantidade, valor) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE valores SET quantidade = ? WHERE idProduto = ? AND valor = ?";
        String sqlCompra = "INSERT INTO compra (idProduto, quantidade, valor) VALUES (?, ?, ?)";

        try {
            Connection conn = BancoDados.getConexaoMySQL();

            PreparedStatement selectStatement = conn.prepareStatement(sqlSelect);
            selectStatement.setInt(1, compra.getIdProduto());
            selectStatement.setDouble(2, compra.getVlrPago());
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int quantidadeAtual = resultSet.getInt("quantidade");
                int novaQuantidade = quantidadeAtual + compra.getQuantidade();

                PreparedStatement updateStatement = conn.prepareStatement(sqlUpdate);
                updateStatement.setInt(1, novaQuantidade);
                updateStatement.setInt(2, compra.getIdProduto());
                updateStatement.setDouble(3, compra.getVlrPago());
                int linhasAfetadas = updateStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Quantidade do produto atualizada!");
                } else {
                    System.out.println("Erro ao atualizar a quantidade do produto.");
                }
            } else {
                PreparedStatement insertStatement = conn.prepareStatement(sqlInsert);
                insertStatement.setInt(1, compra.getIdProduto());
                insertStatement.setInt(2, compra.getQuantidade());
                insertStatement.setDouble(3, compra.getVlrPago());
                int linhasAfetadas = insertStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Novo produto inserido!");
                } else {
                    System.out.println("Erro ao inserir um novo produto.");
                }
            }
            PreparedStatement insertStatement = conn.prepareStatement(sqlCompra);
            insertStatement.setInt(1, compra.getIdProduto());
            insertStatement.setInt(2, compra.getQuantidade());
            insertStatement.setDouble(3, compra.getVlrPago());
            int linhasAfetadas = insertStatement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Novo produto inserido!");
            } else {
                System.out.println("Erro ao inserir um novo produto.");
            }

            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao realizar compra: " + e.getMessage());
        }
        BancoDados.deletarZero();
    }

}
