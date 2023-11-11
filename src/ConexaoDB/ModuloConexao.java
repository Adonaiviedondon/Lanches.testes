package ConexaoDB;

import java.sql.*;

public class ModuloConexao {
	
	private static final String DriverManeger = null;

	public static Connection conector() {
		java.sql.Connection conexao = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/dbDomDomLanches";
		String user = "root";
		String password = "8072";
		
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
			
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

}
