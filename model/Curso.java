package model;

public class Curso {
    private int id;
    private String nombre;
    private int idAlumno; // FK a Alumno

    // Constructor vacío
    public Curso() {
    }

    // Constructor con parámetros
    public Curso(int id, String nombre, int idAlumno) {
        this.id = id;
        this.nombre = nombre;
        this.idAlumno = idAlumno;
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

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idAlumno=" + idAlumno +
                '}';
    }
}
