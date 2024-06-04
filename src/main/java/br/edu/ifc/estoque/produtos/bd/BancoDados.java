package br.edu.ifc.estoque.produtos.bd;

import java.sql.*;

public class BancoDados {
	public static java.sql.Connection getConexaoMySQL() {
		// Atributo do tipo Connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar utilizando o user root");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "celiols", "");
			} catch (SQLException er) {
				System.out.println("Não foi possível conectar utilizando o user celiols");
			}
		}



		return conn;
	}

	public static void deletarZero() {

		String sql = "DELETE FROM valores WHERE quantidade=0";

		try {
			Connection conn = BancoDados.getConexaoMySQL();
			PreparedStatement statement = conn.prepareStatement(sql);

			// executar o sql
			int linhasAfetadas = statement.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Deletado com 0!");
			} else {
				System.out.println("Nada foi deletado");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao deletar: " + e.getMessage());
		}
	}

}
