package br.edu.ifc.estoque.produtos.bd;

import java.sql.*;

public class BancoDados {
    public static java.sql.Connection getConexaoMySQL() {
	// Atributo do tipo Connection
	Connection conn = null;
	
	
	try {
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/estoque", "root", "1234");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return conn;
    }
}
