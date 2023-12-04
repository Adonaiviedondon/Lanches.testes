package ConexaoDB;

import java.sql.*;

public class ModuloConexao {

    public static Connection conector() {
        Connection conexao;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbDomDomLanches";
        String user = "root";
        String password = "8072";

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
            // TODO: handle exception
        }
    }

}
