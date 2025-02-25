package modelos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DbManager.MyException;

public class ArtistaDAO extends DAO<Artista> {

    public ArtistaDAO() {
        super();
    }

    private Artista mapearArtista(ResultSet rs) throws SQLException {
        return new Artista(
                rs.getInt("ID_ARTISTA"),
                rs.getString("NOMBRE"),
                rs.getInt("EDAD"),
                rs.getInt("ID_GRUPO")
        );
    }

    @Override
    public List<Artista> obtenerTodos() throws MyException {
        List<Artista> artistas = new ArrayList<>();
        String consulta = "SELECT * FROM artistas";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                artistas.add(mapearArtista(rs));
            }
        } catch (SQLException e) {
            throw new MyException("❌ Error al obtener los artistas", e);
        }
        return artistas;
    }

    @Override
    public Artista obtenerPorId(int id) throws MyException {
        String consulta = "SELECT * FROM artistas WHERE ID_ARTISTA = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearArtista(rs);
            }
        } catch (SQLException e) {
            throw new MyException("❌ Error al obtener el artista", e);
        }
        return null;
    }

    @Override
    public void insertar(Artista artista) throws MyException {
        try {
            if (existeRegistro("SELECT COUNT(*) FROM artistas WHERE ID_ARTISTA = ?", artista.getId())) {
                System.out.println("⚠️ El artista con ID " + artista.getId() + " ya existe. No se insertará.");
                return;
            }
            ejecutarModificacion(
                    "INSERT INTO artistas (ID_ARTISTA, NOMBRE, EDAD, ID_GRUPO) VALUES (?, ?, ?, ?)",
                    artista.getId(), artista.getNombre(), artista.getEdad(), artista.getIdGrupo()
            );
        } catch (SQLException e) {
            throw new MyException("❌ Error al insertar el artista", e);
        }
    }

    @Override
    public void actualizar(Artista artista) throws MyException {
        try {
            ejecutarModificacion(
                    "UPDATE artistas SET NOMBRE = ?, EDAD = ?, ID_GRUPO = ? WHERE ID_ARTISTA = ?",
                    artista.getNombre(), artista.getEdad(), artista.getIdGrupo(), artista.getId()
            );
        } catch (SQLException e) {
            throw new MyException("❌ Error al actualizar el artista", e);
        }
    }

    @Override
    public void eliminar(int id) throws MyException {
        try {
            ejecutarModificacion("DELETE FROM artistas WHERE ID_ARTISTA = ?", id);
        } catch (SQLException e) {
            throw new MyException("❌ Error al eliminar el artista", e);
        }
    }
}
