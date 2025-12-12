package dao;

import java.util.ArrayList;
import model.Alumno;
import model.Curso;

public class MockInstitutoDAO implements InstitutoDAO {

    private ArrayList<Alumno> alumnos = new ArrayList<>();
    private ArrayList<Curso> cursos = new ArrayList<>();

    @Override
    public void crearTablas() {
        alumnos.clear();
        cursos.clear();
        System.out.println("Tablas simuladas creadas");
    }

    @Override
    public void insertarAlumno(Alumno a) {
        alumnos.add(a);
    }

    @Override
    public void insertarCurso(Curso c) {
        cursos.add(c);
    }

    @Override
    public void actualizarAlumno(Alumno a) {
        for (Alumno al : alumnos) {
            if (al.getId() == a.getId()) {
                al.setNombre(a.getNombre());
                al.setEdad(a.getEdad());
            }
        }
    }

    @Override
    public void actualizarCurso(Curso c) {
        for (Curso cu : cursos) {
            if (cu.getId() == c.getId()) {
                cu.setNombre(c.getNombre());
                cu.setIdAlumno(c.getIdAlumno());
            }
        }
    }

    @Override
    public void listarAlumnos() {
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }

    @Override
    public void listarAlumnosConCursos() {
        for (Alumno a : alumnos) {
            System.out.println(a);
            for (Curso c : cursos) {
                if (c.getIdAlumno() == a.getId()) {
                    System.out.println("  - " + c.getNombre());
                }
            }
        }
    }

    @Override
    public void buscarAlumnoPorId(int id) {
        for (Alumno a : alumnos) {
            if (a.getId() == id) {
                System.out.println(a);
            }
        }
    }
}
