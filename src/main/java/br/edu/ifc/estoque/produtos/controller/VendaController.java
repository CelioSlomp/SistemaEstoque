package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifc.estoque.produtos.bd.BancoDados;
import br.edu.ifc.estoque.produtos.entity.Venda;

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
@RequestMapping("/vendas")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class VendaController {
    @GetMapping("/listaVendas")
    public String listaVendas() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = "";
        String sql = "select produto.nome, cliente.nome, venda.quantidade, venda.valor from produto, cliente, venda where produto.id=venda.idProduto and cliente.id=venda.idCliente;";

        try (Connection conn = BancoDados.getConexaoMySQL();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            List<Venda> vendas = new ArrayList<>();

            while (resultSet.next()) {
                Venda venda = new Venda(
                        resultSet.getString("produto.nome"),
                        resultSet.getString("cliente.nome"),
                        resultSet.getInt("quantidade"),
                        resultSet.getInt("valor"));

                vendas.add(venda);
            }

            jsonResult = objectMapper.writeValueAsString(vendas);
        } catch (SQLException | JsonProcessingException e) {
            System.err.println("Erro ao recuperar compras do banco de dados: " + e.getMessage());
        }

        return jsonResult;
    }

    @PostMapping("/realizarVenda")
    public void realizarVenda(@RequestBody Venda venda) {
        // Imprimir os dados recebidos na tela
        System.out.println("idProduto: " + venda.getIdProduto());
        System.out.println("idCliente: " + venda.getIdCliente());
        System.out.println("quantidade: " + venda.getQuantidade());
        System.out.println("valor: " + venda.getValor());

        if (verificarQuantidade(venda.getIdProduto(), venda.getQuantidade())) {
            String sql = "INSERT INTO venda (idProduto, idCliente, quantidade, valor) VALUES (?, ?, ?, ?)";

            try {
                Connection conn = BancoDados.getConexaoMySQL();
                PreparedStatement statement = conn.prepareStatement(sql);

                // Atribuir valores aos parâmetros
                statement.setInt(1, venda.getIdProduto());
                statement.setInt(2, venda.getIdCliente());
                statement.setInt(3, venda.getQuantidade());
                statement.setDouble(4, venda.getValor());
                // executar o sql
                int linhasAfetadas = statement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Venda Realizada!");
                } else {
                    System.out.println("Venda não realizada");
                }

                retirarQuantidade(venda.getIdProduto(), venda.getQuantidade());

            } catch (SQLException e) {
                System.err.println("Erro ao realizar venda: " + e.getMessage());
            }
        } else {
            System.out.println("Não possui a quantidade mínima");
        }
    }

    boolean verificarQuantidade(int idProduto, int quantidade) {
        String sql = "SELECT SUM(quantidade) as somaQTDE FROM valores GROUP BY idProduto HAVING idProduto="
                + String.valueOf(idProduto);
        int quant = 0;
        try {
            Connection conn = BancoDados.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                quant = resultSet.getInt("somaQTDE");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao realizar venda: " + e.getMessage());
        }

        if (quant >= quantidade) {
            return true;
        }
        return false;

    }

    void retirarQuantidade(int idProduto, int qtde) {
        int qtdPrecisaRet = qtde;
        String sql = "select * from valores where idProduto=" + String.valueOf(idProduto) + " order by valor desc";

        try (Connection conn = BancoDados.getConexaoMySQL();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int retirar = resultSet.getInt("quantidade");
                int valor = resultSet.getInt("valor");

                if (qtdPrecisaRet > retirar) {
                    qtdPrecisaRet -= retirar;
                    retirar = 0;
                } else {
                    retirar -= qtdPrecisaRet;
                    qtdPrecisaRet = 0;
                }

                String sqlUpdate = "UPDATE valores SET quantidade = " + String.valueOf(retirar) +
                        " WHERE idProduto = " + String.valueOf(idProduto) + " AND valor = " + String.valueOf(valor);

                PreparedStatement updateStatement = conn.prepareStatement(sqlUpdate);

                int linhasAfetadas = updateStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Quantidade do produto atualizada!");
                } else {
                    System.out.println("Erro ao atualizar a quantidade do produto.");
                }

                BancoDados.deletarZero();

                if (qtdPrecisaRet == 0)
                    break;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar compras do banco de dados: " + e.getMessage());
        }

    }
}
