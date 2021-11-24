package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();//Conecta com o banco de dados
			//Recebe o comando SQL para inserir dados
			st = conn.prepareStatement(""
					+ "INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"/*Campos a serem alterados*/
					+ "VALUES"/*valores a serem inseridos*/
					+ "(?, ?, ?, ?, ?)",/*placeholder para futuramente receber os valores a serem inseridos*/
					Statement.RETURN_GENERATED_KEYS //Recupera o ID do objeto inserido no banco
					);
			st.setString(1, "Carl Purple");//Troca o primeiro placeholder pelo valor definido
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));//Instancia uma nova data SQL no campo "BirthDate"
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
		
//			//Insere dois novos departamentos na tabela Departments
//			
//			st = conn.prepareStatement(
//					"insert into department (Name) values ('D1'),('D2')",
//					Statement.RETURN_GENERATED_KEYS
//					);
							//Executa as operações feitas acima
			int rowsAffected = st.executeUpdate();//Retorna um numero inteiro indicando quantas linhas foram afetadas
			
			
			//Retorna ID do objeto inserido
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id );
				}
			}
			else {
				System.out.println("No rows affected!");
			}
		}
		//Tratamento de exceções
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		//Fecha os recursos abertos
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
