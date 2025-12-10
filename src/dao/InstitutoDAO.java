package dao;

import model.Alumno;
import model.Curso;

public interface InstitutoDAO {

    void crearTablas();

    void insertarAlumno(Alumno a);
    void insertarCurso(Curso c);

    void actualizarAlumno(Alumno a);
    void actualizarCurso(Curso c);

    void listarAlumnos();
    void listarAlumnosConCursos();

    void buscarAlumnoPorId(int id);
}
