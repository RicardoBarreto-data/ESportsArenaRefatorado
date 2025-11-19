
package esportarena.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;

public class ConexaoMySQL {

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Properties props = new Properties();

            // Carrega o arquivo db.properties
            InputStream input = ConexaoMySQL.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (input == null) {
                throw new RuntimeException("Arquivo db.properties não encontrado no classpath!");
            }

            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
