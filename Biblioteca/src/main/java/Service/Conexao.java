package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL= "jdbc:postgresql://localhost:5432/Biblioteca";

    private static final String USERNAME= "postgres";
    private static final String PASSWORD= "123456";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection
                (URL, USERNAME,
                        PASSWORD);

    }
}
