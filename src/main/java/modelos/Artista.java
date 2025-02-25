package modelos;

public class Artista {
    private int id;
    private String nombre;
    private int edad;
    private int idGrupo;

    public Artista(int id, String nombre, int edad, int idGrupo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.idGrupo = idGrupo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    // Método toString() para que se imprima correctamente en la consola
    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Edad: " + edad + " | Grupo ID: " + idGrupo;
    }
}
