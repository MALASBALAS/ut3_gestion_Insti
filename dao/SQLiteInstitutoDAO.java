package dao;

import java.sql.*;
import model.Alumno;
import model.Curso;

public class SQLiteInstitutoDAO implements InstitutoDAO {

    private static final String DATABASE_NAME = "mybasedatos.db";
    private Connection con;

    public SQLiteInstitutoDAO() {
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
        try (Statement st = con.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS alumnos (" +
                       "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       "nombre TEXT, edad INTEGER)");
            st.execute("CREATE TABLE IF NOT EXISTS cursos (" +
                       "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       "nombre TEXT, id_alumno INTEGER, " +
                       "FOREIGN KEY(id_alumno) REFERENCES alumnos(id))");
        } catch (SQLException e) {
            System.out.println("Error creando tablas");
        }
    }

    @Override
    public void insertarAlumno(Alumno a) {
        try (PreparedStatement ps =
             con.prepareStatement("INSERT INTO alumnos(nombre,edad) VALUES (?,?)")) {
            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getEdad());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insertando alumno");
        }
    }

    @Override
    public void insertarCurso(Curso c) {
        try (PreparedStatement ps =
             con.prepareStatement("INSERT INTO cursos(nombre,id_alumno) VALUES (?,?)")) {
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdAlumno());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insertando curso");
        }
    }

    @Override
    public void actualizarAlumno(Alumno a) {
        try (PreparedStatement ps =
             con.prepareStatement("UPDATE alumnos SET nombre=?, edad=? WHERE id=?")) {
            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getEdad());
            ps.setInt(3, a.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando alumno");
        }
    }

    @Override
    public void actualizarCurso(Curso c) {
        try (PreparedStatement ps =
             con.prepareStatement("UPDATE cursos SET nombre=?, id_alumno=? WHERE id=?")) {
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdAlumno());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando curso");
        }
    }

    @Override
    public void listarAlumnos() {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM alumnos")) {
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("nombre") + " " +
                    rs.getInt("edad"));
            }
        } catch (SQLException e) {
            System.out.println("Error listando alumnos");
        }
    }

    @Override
    public void listarAlumnosConCursos() {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                 "SELECT a.nombre, c.nombre FROM alumnos a " +
                 "LEFT JOIN cursos c ON a.id=c.id_alumno")) {

            while (rs.next()) {
                System.out.println(
                    rs.getString(1) + " - " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Error listando relación");
        }
    }

    @Override
    public void buscarAlumnoPorId(int id) {
        try (PreparedStatement ps =
             con.prepareStatement("SELECT * FROM alumnos WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error buscando alumno");
        }
    }
}
