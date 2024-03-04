package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        return "lista_compras";
    }

    @PostMapping("/realizarCompra")
    public void realizarCompra(@RequestBody Compra compra) {
        // Imprimir os dados recebidos na tela
        System.out.println("id: " + compra.getId());
        System.out.println("quantidade: " + compra.getQuantidade());
        System.out.println("valor: " + compra.getVlrPago());

        String sql = "INSERT INTO valores (idProduto, quantidade, valor) VALUES (?, ?, ?)";

        try {
            Connection conn = BancoDados.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement(sql);

            // Atribuir valores aos parâmetros
            statement.setInt(1, compra.getId());
            statement.setInt(2, compra.getQuantidade());
            statement.setDouble(3, compra.getVlrPago());

            // executar o sql
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Compra Realizada!");
            } else {
                System.out.println("Compra não Realizada");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao realizar compra: " + e.getMessage());
        }
        BancoDados.deletarZero();
    }
}
