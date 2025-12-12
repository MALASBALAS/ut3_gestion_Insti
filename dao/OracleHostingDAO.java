package dao;

import java.sql.*;
import model.Servidor;
import model.Web;

// DAO Oracle
public class OracleHostingDAO implements HostingDAO {

    private Connection con;

    // Conecta BD Oracle
    public OracleHostingDAO() {
        try {
            String host = System.getenv().getOrDefault("ORACLE_HOST", "192.168.56.101");
            String port = System.getenv().getOrDefault("ORACLE_PORT", "1521");
            String service = System.getenv().getOrDefault("ORACLE_SERVICE", "XEPDB1");
            String user = System.getenv().getOrDefault("ORACLE_USER", "Tierno");
            String pass = System.getenv().getOrDefault("ORACLE_PASSWORD", "Oracle123");

            String url = String.format("jdbc:oracle:thin:@//%s:%s/%s", host, port, service);

            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado a Oracle XE correctamente.");
        } catch (SQLException e) {
            System.out.println("Error conexión Oracle: " + e.getMessage());
        }
    }

    @Override
    public void crearTablas() {
        // Crea tablas BD
        try (Statement st = con.createStatement()) {

            try { st.execute("DROP TABLE Webs"); } catch (SQLException e) {}
            try { st.execute("DROP TABLE Servidor"); } catch (SQLException e) {}

            st.execute("""
                CREATE TABLE Servidor (
                    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    nombre VARCHAR2(100),
                    ip VARCHAR2(50),
                    ubicacion VARCHAR2(100)
                )
            """);

            st.execute("""
                CREATE TABLE Webs (
                    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    dominio VARCHAR2(100),
                    lenguaje VARCHAR2(50),
                    idServidor NUMBER,
                    FOREIGN KEY(idServidor) REFERENCES Servidor(id)
                )
            """);

            System.out.println("Tablas Oracle creadas correctamente.");

        } catch (SQLException e) {
            System.out.println("Error creando tablas: " + e.getMessage());
        }
    }

    @Override
    public void insertarServidor(Servidor s) {
        // Inserta servidor BD
        try (PreparedStatement ps =
            con.prepareStatement("INSERT INTO Servidor(nombre, ip, ubicacion) VALUES (?, ?, ?)")) {

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
            con.prepareStatement("INSERT INTO Webs(dominio, lenguaje, idServidor) VALUES (?, ?, ?)")) {

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
                SELECT s.nombre AS servidor, w.dominio AS web
                FROM Servidor s
                LEFT JOIN Webs w ON s.id = w.idServidor
            """)) {

            while (rs.next()) {
                System.out.println(rs.getString("servidor") + " → " + rs.getString("web"));
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
                    rs.getString("nombre") + " | IP: " +
                    rs.getString("ip") + " | Ubicación: " +
                    rs.getString("ubicacion")
                );
            } else {
                System.out.println("Servidor no encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Error buscando Servidor: " + e.getMessage());
        }
    }
}
