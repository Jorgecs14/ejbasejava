package Main;

import DbManager.MyException;
import Gestores.GestorDatos;
import modelos.ArtistaDAO;
import modelos.GrupoDAO;

public class Main {
    public static void main(String[] args) {
        try {
            GrupoDAO grupoDAO = new GrupoDAO();
            ArtistaDAO artistaDAO = new ArtistaDAO();


            GestorDatos.manejarGrupo(grupoDAO);
            GestorDatos.manejarArtista(artistaDAO);

        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
