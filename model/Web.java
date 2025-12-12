package model;

// Entidad Web
public class Web {
    private int id;
    private String dominio;
    private String lenguaje;
    private int idServidor;

    public Web() {}

    public Web(int id, String dominio, String lenguaje, int idServidor) {
        this.id = id;
        this.dominio = dominio;
        this.lenguaje = lenguaje;
        this.idServidor = idServidor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDominio() { return dominio; }
    public void setDominio(String dominio) { this.dominio = dominio; }

    public String getLenguaje() { return lenguaje; }
    public void setLenguaje(String lenguaje) { this.lenguaje = lenguaje; }

    public int getIdServidor() { return idServidor; }
    public void setIdServidor(int idServidor) { this.idServidor = idServidor; }

    @Override
    public String toString() {
        return id + " - " + dominio + " (" + lenguaje + ") → Servidor " + idServidor;
    }
}
