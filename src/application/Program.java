package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;  
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();//Conecta ao banco de dados
			
			st = conn.createStatement();//Objeto de tipo statement instanciado
			
			rs = st.executeQuery("select * from department");//Recebe o comando sql a ser executado
			
			while(rs.next()) {//Percorre a tabela enquanto houver elementos
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));//Retorna a coluna Id e a coluna Name da tabela
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
