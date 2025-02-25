package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DbManager.MyException;


public class GrupoDAO extends DAO<Grupo> {

    public GrupoDAO() {
        super();
    }




        private Grupo mapearGrupo(ResultSet rs) throws SQLException {
            return new Grupo(
                    rs.getInt("ID_GRUPO"),
                    rs.getString("NOMBRE"),
                    rs.getString("PAIS"),
                    rs.getInt("COD_FECHA_CREACION")
            );
        }

        @Override
        public List<Grupo> obtenerTodos() throws MyException {
            List<Grupo> grupos = new ArrayList<>();
            String consulta = "SELECT * FROM grupos";

            try (PreparedStatement stmt = conexion.prepareStatement(consulta);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    grupos.add(mapearGrupo(rs));
                }
            } catch (SQLException e) {
                throw new MyException("❌ Error al obtener los grupos", e);
            }
            return grupos;
        }

        @Override
        public Grupo obtenerPorId(int id) throws MyException {
            String consulta = "SELECT * FROM grupos WHERE ID_GRUPO = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return mapearGrupo(rs);
                }
            } catch (SQLException e) {
                throw new MyException("❌ Error al obtener el grupo", e);
            }
            return null;
        }

        @Override
        public void insertar(Grupo grupo) throws MyException {
            try {
                if (existeRegistro("SELECT COUNT(*) FROM grupos WHERE ID_GRUPO = ?", grupo.getId())) {
                    System.out.println("⚠️ El grupo con ID " + grupo.getId() + " ya existe. No se insertará.");
                    return;
                }
                ejecutarModificacion(
                        "INSERT INTO grupos (ID_GRUPO, NOMBRE, PAIS, COD_FECHA_CREACION) VALUES (?, ?, ?, ?)",
                        grupo.getId(), grupo.getNombre(), grupo.getPais(), grupo.getFechaCreacion()
                );
            } catch (SQLException e) {
                throw new MyException("❌ Error al insertar el grupo", e);
            }
        }

        @Override
        public void actualizar(Grupo grupo) throws MyException {
            try {
                ejecutarModificacion(
                        "UPDATE grupos SET NOMBRE = ?, PAIS = ?, COD_FECHA_CREACION = ? WHERE ID_GRUPO = ?",
                        grupo.getNombre(), grupo.getPais(), grupo.getFechaCreacion(), grupo.getId()
                );
            } catch (SQLException e) {
                throw new MyException("❌ Error al actualizar el grupo", e);
            }
        }

        @Override
        public void eliminar(int id) throws MyException {
            try {
                ejecutarModificacion("DELETE FROM grupos WHERE ID_GRUPO = ?", id);
            } catch (SQLException e) {
                throw new MyException("❌ Error al eliminar el grupo", e);
            }
        }
    }
