package Gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import DbManager.MyException;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("URL");
    private static final String USUARIO = dotenv.get("USUARIO");
    private static final String PASSWORD = dotenv.get("PASSWORD");

    private static Connection conexion;

    public static Connection connect() throws MyException {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("✅ Conexión exitosa.");
            } catch (SQLException e) {
                throw new MyException("❌ Error al conectar a la base de datos", e);
            }
        }
        return conexion;
    }

    public static void disconnect() throws MyException {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexion = null;
                System.out.println("✅ Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            throw new MyException("❌ Error al cerrar la conexión", e);
        }
    }
}

