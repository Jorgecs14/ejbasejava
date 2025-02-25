package modelos;

public class Grupo {
    private int id;
    private String nombre;
    private String pais;
    private int fechaCreacion;

    public Grupo(int id, String nombre, String pais, int fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fechaCreacion = fechaCreacion;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(int fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | País: " + pais + " | Año Creación: " + fechaCreacion;
    }
}
