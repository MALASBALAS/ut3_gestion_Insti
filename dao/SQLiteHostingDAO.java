package dao;

import java.sql.*;
import model.Servidor;
import model.Web;

// DAO SQLite
public class SQLiteHostingDAO implements HostingDAO {

    private static final String DATABASE_NAME = "serversdatabase.db";
    private Connection con;

    // Conecta BD SQLite
    public SQLiteHostingDAO() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
            try (Statement st = con.createStatement()) {
                st.execute("PRAGMA foreign_keys = ON");
            }
        } catch (SQLException e) {
            System.out.println("Error conexión SQLite: " + e.getMessage());
        }
    }

    @Override
    public void crearTablas() {
        // Crea tablas BD
        try (Statement st = con.createStatement()) {

            st.execute("DROP TABLE IF EXISTS Webs");
            st.execute("DROP TABLE IF EXISTS Servidor");

            st.execute("""
                CREATE TABLE Servidor (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT,
                    ip TEXT,
                    ubicacion TEXT
                )
            """);

            st.execute("""
                CREATE TABLE Webs (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    dominio TEXT,
                    lenguaje TEXT,
                    idServidor INTEGER,
                    FOREIGN KEY(idServidor) REFERENCES Servidor(id)
                )
            """);

            System.out.println("Tablas SQLite creadas correctamente.");

        } catch (SQLException e) {
            System.out.println("Error creando tablas: " + e.getMessage());
        }
    }

    @Override
    public void insertarServidor(Servidor s) {
        // Inserta servidor BD
        try (PreparedStatement ps =
             con.prepareStatement("INSERT INTO Servidor(nombre, ip, ubicacion) VALUES (?,?,?)")) {

            ps.setString(1, s.getNombre());
            ps.setString(2, s.getIp());
            ps.setString(3, s.getUbicacion());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertando Servidor: " + e.getMessage());
        }
    }

    @Override
    public void insertarWeb(Web w) {
        // Inserta web BD
        try (PreparedStatement ps =
             con.prepareStatement("INSERT INTO Webs(dominio, lenguaje, idServidor) VALUES (?,?,?)")) {

            ps.setString(1, w.getDominio());
            ps.setString(2, w.getLenguaje());
            ps.setInt(3, w.getIdServidor());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertando Web: " + e.getMessage());
        }
    }

    @Override
    public void actualizarServidor(Servidor s) {
        // Actualiza servidor BD
        try (PreparedStatement ps =
             con.prepareStatement("UPDATE Servidor SET nombre=?, ip=?, ubicacion=? WHERE id=?")) {

            ps.setString(1, s.getNombre());
            ps.setString(2, s.getIp());
            ps.setString(3, s.getUbicacion());
            ps.setInt(4, s.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizando Servidor: " + e.getMessage());
        }
    }

    @Override
    public void actualizarWeb(Web w) {
        // Actualiza web BD
        try (PreparedStatement ps =
             con.prepareStatement("UPDATE Webs SET dominio=?, lenguaje=?, idServidor=? WHERE id=?")) {

            ps.setString(1, w.getDominio());
            ps.setString(2, w.getLenguaje());
            ps.setInt(3, w.getIdServidor());
            ps.setInt(4, w.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizando Web: " + e.getMessage());
        }
    }

    @Override
    public void listarServidores() {
        // Lista todos servidores
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Servidor")) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("nombre") + " | IP: " +
                    rs.getString("ip") + " | Ubicación: " +
                    rs.getString("ubicacion")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error listando servidores: " + e.getMessage());
        }
    }

    @Override
    public void listarServidoresConWebs() {
        // Lista servidores webs
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("""
                SELECT s.nombre, w.dominio
                FROM Servidor s
                LEFT JOIN Webs w ON s.id = w.idServidor
            """)) {

            while (rs.next()) {
                System.out.println(
                    rs.getString(1) + " → Web: " + rs.getString(2)
                );
            }

        } catch (SQLException e) {
            System.out.println("Error listando relación: " + e.getMessage());
        }
    }

    @Override
    public void buscarServidorPorId(int id) {
        // Busca por ID
        try (PreparedStatement ps =
             con.prepareStatement("SELECT * FROM Servidor WHERE id=?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                    "Servidor encontrado: " +
                    rs.getString("nombre") + " | " +
                    rs.getString("ip") + " | " +
                    rs.getString("ubicacion")
                );
            } else {
                System.out.println("No existe un servidor con ID " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error buscando servidor: " + e.getMessage());
        }
    }
}
