package DbManager;


import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("URL");
    private static final String USUARIO = dotenv.get("USUARIO");
    private static final String PASSWORD = dotenv.get("PASSWORD");

    private Connection connection;

    public void connect() throws MyException {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✅ Conexión exitosa.");
        } catch (SQLException e) {
            throw new MyException("❌ Error al conectar a la base de datos", e);
        }
    }


    public void disconnect() throws MyException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            throw new MyException("❌ Error al cerrar la conexión", e);
        }
    }

}

