package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {
// Atualizando dados na tabela
	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();//Conecta com o banco de dados
			
			conn.setAutoCommit(false);//Não confirmará automaticamente as alterações feitas
			
			st = conn.createStatement();

			//Executa todo o bloco de alterações
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
//			int x = 1;
//			if (x < 2) {
//				throw new SQLException("Fake error");
//			}
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			System.out.println("Rows1: " + rows1);
			System.out.println("Rows2: " + rows2);
			
			//Apenas no final confirma as alterações feitas, e as aplica
			conn.commit();
		}
		catch(SQLException e) {
			try {
				//Caso tenha ocorrido algum erro, volta ao estado anterior as alterações
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
				//
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DbException("Error trying to roll back! Causd by: " + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
