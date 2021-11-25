package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {
// Atualizando dados na tabela
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();//Conecta com o banco de dados
			st = conn.prepareStatement(
					 "UPDATE seller "
					+"SET BaseSalary = BaseSalary + ? "//placeholder que receberá o valor a ser incrementado
					+"WHERE (DepartmentId = ?)"//placeholder que receberá o id do departamento
					);//Recebe a query
			st.setDouble(1, 200.00);//Valor que será aplicado ao primeiro placeholder
			st.setInt(2, 2);//Valor que será aplicado ao segundo placeholder
			
			int rows = st.executeUpdate();
			
			String result = rows > 0 ? "Done! " + rows +" affected" : "No rows affected";
			
			System.out.println(result);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
