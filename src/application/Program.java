package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {
// Atualizando dados na tabela
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();//Conecta com o banco de dados
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE Id = ?"
					);//Recebe a query
			st.setInt(1, 2);//Deleta todos os departamentos cujo Id seja igual ao segundo parametro passado

			
			int rows = st.executeUpdate();
			
			String result = rows > 0 ? "Done! " + rows +" affected" : "No rows affected";
			
			System.out.println(result);
		}
		catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
