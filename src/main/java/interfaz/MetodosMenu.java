package interfaz;
import DbManager.MyException;
import modelos.Artista;
import modelos.ArtistaDAO;
import modelos.Grupo;
import modelos.GrupoDAO;
import java.util.List;
import java.util.Scanner;



public class MetodosMenu {
    protected static final Scanner scanner = new Scanner(System.in);
    protected static final GrupoDAO grupoDAO = new GrupoDAO();
    protected static final ArtistaDAO artistaDAO = new ArtistaDAO();

    protected static void mostrarMenu() {
        System.out.println("\n📌 Menú Principal:");
        System.out.println("1️⃣ Gestionar Grupos");
        System.out.println("2️⃣ Gestionar Artistas");
        System.out.println("0️⃣ Salir");
    }

    protected static void manejarGrupo() {
        int opcion;
        do {
            System.out.println("\n🎸 Menú de Grupos:");
            System.out.println("1️⃣ Insertar grupo");
            System.out.println("2️⃣ Listar grupos");
            System.out.println("3️⃣ Buscar grupo por ID");
            System.out.println("4️⃣ Actualizar grupo");
            System.out.println("5️⃣ Eliminar grupo");
            System.out.println("0️⃣ Volver al menú principal");
            System.out.print("\nElige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> insertarGrupo();
                case 2 -> listarGrupos();
                case 3 -> buscarGrupo();
                case 4 -> actualizarGrupo();
                case 5 -> eliminarGrupo();
                case 0 -> System.out.println("🔙 Volviendo al menú principal...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    protected static void manejarArtista() {
        int opcion;
        do {
            System.out.println("\n🎤 Menú de Artistas:");
            System.out.println("1️⃣ Insertar artista");
            System.out.println("2️⃣ Listar artistas");
            System.out.println("3️⃣ Buscar artista por ID");
            System.out.println("4️⃣ Actualizar artista");
            System.out.println("5️⃣ Eliminar artista");
            System.out.println("0️⃣ Volver al menú principal");
            System.out.print("\nElige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> insertarArtista();
                case 2 -> listarArtistas();
                case 3 -> buscarArtista();
                case 4 -> actualizarArtista();
                case 5 -> eliminarArtista();
                case 0 -> System.out.println("🔙 Volviendo al menú principal...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }


    protected static void insertarGrupo() {
        System.out.print("📌 ID del grupo: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("📌 Nombre del grupo: ");
        String nombre = scanner.nextLine();
        System.out.print("📌 País: ");
        String pais = scanner.nextLine();
        System.out.print("📌 Código de fecha de creación: ");
        int fechaCreacion = scanner.nextInt();

        Grupo grupo = new Grupo(id, nombre, pais, fechaCreacion);
        try {
            grupoDAO.insertar(grupo);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    protected static void listarGrupos() {
        try {
            List<Grupo> grupos = grupoDAO.obtenerTodos();
            System.out.println("\n📜 Lista de Grupos:");
            for (Grupo grupo : grupos) {
                System.out.println(grupo);
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    protected static void buscarGrupo() {
        System.out.print("🔎 ID del grupo a buscar: ");
        int id = scanner.nextInt();
        try {
            Grupo grupo = grupoDAO.obtenerPorId(id);
            if (grupo != null) {
                System.out.println("✅ Grupo encontrado: " + grupo);
            } else {
                System.out.println("❌ Grupo no encontrado.");
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    protected static void actualizarGrupo() {
        System.out.print("✏️ ID del grupo a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("✏️ Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("✏️ Nuevo país: ");
        String pais = scanner.nextLine();
        System.out.print("✏️ Nuevo código de fecha de creación: ");
        int fechaCreacion = scanner.nextInt();

        Grupo grupo = new Grupo(id, nombre, pais, fechaCreacion);
        try {
            grupoDAO.actualizar(grupo);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    protected static void eliminarGrupo() {
        System.out.print("🗑️ ID del grupo a eliminar: ");
        int id = scanner.nextInt();
        try {
            grupoDAO.eliminar(id);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }


    protected static void insertarArtista() {
        System.out.print("📌 ID del artista: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("📌 Nombre del artista: ");
        String nombre = scanner.nextLine();
        System.out.print("📌 Edad: ");
        int edad = scanner.nextInt();
        System.out.print("📌 ID del grupo: ");
        int idGrupo = scanner.nextInt();

        Artista artista = new Artista(id, nombre, edad, idGrupo);
        try {
            artistaDAO.insertar(artista);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    protected static void listarArtistas() {
        try {
            List<Artista> artistas = artistaDAO.obtenerTodos();
            System.out.println("\n📜 Lista de Artistas:");
            for (Artista artista : artistas) {
                System.out.println(artista);
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    protected static void buscarArtista() {
        System.out.print("🔎 ID del artista a buscar: ");
        int id = scanner.nextInt();
        try {
            Artista artista = artistaDAO.obtenerPorId(id);
            if (artista != null) {
                System.out.println("✅ Artista encontrado: " + artista);
            } else {
                System.out.println("❌ Artista no encontrado.");
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    protected static void actualizarArtista() {
        System.out.print("✏️ ID del artista a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("✏️ Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("✏️ Nueva edad: ");
        int edad = scanner.nextInt();
        System.out.print("✏️ Nuevo ID de grupo: ");
        int idGrupo = scanner.nextInt();

        Artista artista = new Artista(id, nombre, edad, idGrupo);
        try {
            artistaDAO.actualizar(artista);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    protected static void eliminarArtista() {
        System.out.print("🗑️ ID del artista a eliminar: ");
        int id = scanner.nextInt();
        try {
            artistaDAO.eliminar(id);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
