package model;

// Entidad Servidor
public class Servidor {
    private int id;
    private String nombre;
    private String ip;
    private String ubicacion;

    public Servidor() {}

    public Servidor(int id, String nombre, String ip, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ip = ip;
        this.ubicacion = ubicacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    @Override
    public String toString() {
        return id + " - " + nombre + " [" + ip + "] (" + ubicacion + ")";
    }
}
