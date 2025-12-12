package dao;

import model.Servidor;
import model.Web;

// Interfaz DAO
public interface HostingDAO {
    void crearTablas();

    void insertarServidor(Servidor s);
    void insertarWeb(Web w);

    void actualizarServidor(Servidor s);
    void actualizarWeb(Web w);

    void listarServidores();
    void listarServidoresConWebs();

    void buscarServidorPorId(int id);
}

