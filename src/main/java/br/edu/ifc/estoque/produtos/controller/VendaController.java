package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifc.estoque.produtos.bd.BancoDados;
import br.edu.ifc.estoque.produtos.entity.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        return "lista_vendas";
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

            /*
             * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * * PRECISA ARRUMAR PRA RETIRAR DA QUANTIDADE DO PRODUTO
             * 
             */

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
}
