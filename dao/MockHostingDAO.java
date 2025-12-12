package dao;

import java.util.ArrayList;
import model.Servidor;
import model.Web;

// DAO Mock
public class MockHostingDAO implements HostingDAO {

    private ArrayList<Servidor> servidores = new ArrayList<>();
    private ArrayList<Web> webs = new ArrayList<>();

    @Override
    public void crearTablas() {
        // Limpia listas
        servidores.clear();
        webs.clear();
        System.out.println("Tablas simuladas creadas.");
    }

    @Override
    public void insertarServidor(Servidor s) {
        // Añade servidor
        servidores.add(s);
    }

    @Override
    public void insertarWeb(Web w) {
        // Añade web
        webs.add(w);
    }

    @Override
    public void actualizarServidor(Servidor s) {
        // Actualiza servidor
        for (Servidor serv : servidores) {
            if (serv.getId() == s.getId()) {
                serv.setNombre(s.getNombre());
                serv.setIp(s.getIp());
                serv.setUbicacion(s.getUbicacion());
            }
        }
    }

    @Override
    public void actualizarWeb(Web w) {
        // Actualiza web
        for (Web web : webs) {
            if (web.getId() == w.getId()) {
                web.setDominio(w.getDominio());
                web.setLenguaje(w.getLenguaje());
                web.setIdServidor(w.getIdServidor());
            }
        }
    }

    @Override
    public void listarServidores() {
        // Lista todos servidores
        for (Servidor s : servidores) {
            System.out.println(s);
        }
    }

    @Override
    public void listarServidoresConWebs() {
        // Lista servidores webs
        for (Servidor s : servidores) {
            System.out.println(s);
            for (Web w : webs) {
                if (w.getIdServidor() == s.getId()) {
                    System.out.println("   - " + w.getDominio() + " (" + w.getLenguaje() + ")");
                }
            }
        }
    }

    @Override
    public void buscarServidorPorId(int id) {
        // Busca por ID
        for (Servidor s : servidores) {
            if (s.getId() == id) {
                System.out.println(s);
            }
        }
    }
}
