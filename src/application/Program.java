package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {
// Versão alternativa nao verbosa do programa
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			// Query a ser executada
			String query = "insert into seller values (null,'Marcos Menezes','Marcos@gmail.com','1995/06/23','3600.00','2')";

			// Inicia a conexão com o banco de dados, e executa a query 
			conn = DB.getConnection();
			st = conn.prepareStatement(query);

			// Guarda o numero de rows afetadas
			int rows = st.executeUpdate();

			// Verifica se alguma coluna foi afetada, e retorna uma String
			String result = rows > 0 ? rows + " rows affecteds!" : rows + " rows affecteds!";
			System.out.println(result);
		}

		catch (SQLException e) {
			e.getMessage();
		}

		finally {
			// Fecha as conexões
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
