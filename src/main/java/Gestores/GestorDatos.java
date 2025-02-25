package Gestores;

import DbManager.MyException;
import modelos.Artista;
import modelos.ArtistaDAO;
import modelos.Grupo;
import modelos.GrupoDAO;

import java.util.List;

public class GestorDatos {

    public static void manejarGrupo(GrupoDAO grupoDAO) throws MyException {

        Grupo grupo = new Grupo(16, "Nirvana", "USA", 198703);
        grupoDAO.insertar(grupo);


        imprimirLista(grupoDAO.obtenerTodos(), "Grupos:");


        grupo = grupoDAO.obtenerPorId(16);
        if (grupo != null) {
            System.out.println("Grupo encontrado: " + grupo.getNombre());
        }


        grupo.setNombre("Nirvana Updated");
        grupoDAO.actualizar(grupo);


        grupoDAO.eliminar(14);
    }

    public static void manejarArtista(ArtistaDAO artistaDAO) throws MyException {
        Artista artista = new Artista(161, "Kurt Cobain", 50, 12);
        artistaDAO.insertar(artista);


        imprimirLista(artistaDAO.obtenerTodos(), "Artistas:");


        artista = artistaDAO.obtenerPorId(161);
        if (artista != null) {
            System.out.println("Artista encontrado: " + artista.getNombre());
        }


        artista.setNombre("Kurt Cobain Updated");
        artistaDAO.actualizar(artista);


        artistaDAO.eliminar(102);
    }

    public static <T> void imprimirLista(List<T> lista, String titulo) {
        System.out.println("\n" + titulo);
        for (T item : lista) {
            if (item instanceof Grupo) {
                Grupo grupo = (Grupo) item;
                System.out.println(grupo.getId() + " - " + grupo.getNombre() + " - " + grupo.getPais() + " - " + grupo.getFechaCreacion());
            } else if (item instanceof Artista) {
                Artista artista = (Artista) item;
                System.out.println(artista.getId() + " - " + artista.getNombre() + " - " + artista.getEdad() + " - Grupo ID: " + artista.getIdGrupo());
            } else {
                System.out.println(item);
            }
        }
    }

}
