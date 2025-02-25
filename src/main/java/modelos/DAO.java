package modelos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import DbManager.MyException;
import Gestores.DatabaseConnection;

public abstract class DAO<T> {
    protected Connection conexion;

    public DAO() {
        try {
            this.conexion = DatabaseConnection.connect();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }


    protected boolean existeRegistro(String consulta, int id) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }


    private void setParametros(PreparedStatement stmt, Object... parametros) throws SQLException {
        for (int i = 0; i < parametros.length; i++) {
            if (parametros[i] instanceof Integer) {
                stmt.setInt(i + 1, (Integer) parametros[i]);
            } else if (parametros[i] instanceof String) {
                stmt.setString(i + 1, (String) parametros[i]);
            } else if (parametros[i] instanceof java.util.Date) {
                stmt.setDate(i + 1, new java.sql.Date(((java.util.Date) parametros[i]).getTime()));
            } else if (parametros[i] instanceof Boolean) {
                stmt.setBoolean(i + 1, (Boolean) parametros[i]);
            }
        }
    }


    protected void ejecutarModificacion(String consulta, Object... parametros) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            setParametros(stmt, parametros);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Operación realizada correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el registro afectado.");
            }
        }
    }


    public abstract List<T> obtenerTodos() throws MyException;
    public abstract T obtenerPorId(int id) throws MyException;
    public abstract void insertar(T entidad) throws MyException;
    public abstract void actualizar(T entidad) throws MyException;
    public abstract void eliminar(int id) throws MyException;


}


