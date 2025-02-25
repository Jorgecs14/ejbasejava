package interfaz;
import java.util.Scanner;
import static interfaz.MetodosMenu.*;

public class menu {
    private static final Scanner scanner = new Scanner(System.in);;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("\nElige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> manejarGrupo();
                case 2 -> manejarArtista();
                case 0 -> System.out.println("🚪 Saliendo del programa...");
                default -> System.out.println("❌ Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 0);
    }
}

