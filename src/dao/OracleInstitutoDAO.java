package dao;

import java.sql.*;
import model.Alumno;
import model.Curso;

public class OracleInstitutoDAO implements InstitutoDAO {

    private Connection con;

    public OracleInstitutoDAO() {
        try {
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                "oracle",
                "Oracle123"
            );
        } catch (SQLException e) {
            System.out.println("Error conexión Oracle");
        }
    }

    @Override
    public void crearTablas() {
        try (Statement st = con.createStatement()) {

            try { st.execute("DROP TABLE cursos"); } catch (SQLException e) {}
            try { st.execute("DROP TABLE alumnos"); } catch (SQLException e) {}

            st.execute(
                "CREATE TABLE alumnos (" +
                "id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "nombre VARCHAR2(100), edad NUMBER)"
            );

            st.execute(
                "CREATE TABLE cursos (" +
                "id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "nombre VARCHAR2(100), id_alumno NUMBER, " +
                "FOREIGN KEY(id_alumno) REFERENCES alumnos(id))"
            );

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
                 "LEFT JOIN cursos c ON a.id = c.id_alumno")) {

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
