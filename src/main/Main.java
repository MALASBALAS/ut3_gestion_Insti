package main;

import dao.*;
import java.util.Scanner;
import model.Alumno;
import model.Curso;

public class Main {
    
    private static final Scanner sc = new Scanner(System.in);
    private static InstitutoDAO dao;
    
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("SISTEMA GESTIÓN INSTITUTO");
        System.out.println("2DAM - Acceso a Datos - UT3");
        System.out.println("\n");
        
        dao = seleccionarConexion();
        
        if (dao != null) {
            boolean continuar = true;
            while (continuar) {
                continuar = menuOperaciones();
            }
        }
        
        System.out.println("\n¡Hasta luego!");
        sc.close();
    }
    
    private static InstitutoDAO seleccionarConexion() {
        System.out.println("MENÚ 1: TIPO DE CONEXIÓN");
        System.out.println("1. Mock (simulación)");
        System.out.println("2. SQLite");
        System.out.println("3. Oracle");
        System.out.println("0. Salir");
        System.out.print("\nOpción: ");
        
        int opcion = leerInt();
        
        switch(opcion) {
            case 1: return new MockInstitutoDAO();
            case 2: return new SQLiteInstitutoDAO();
            case 3: return new OracleInstitutoDAO();
            case 0: return null;
            default:
                System.out.println("Opción inválida");
                return seleccionarConexion();
        }
    }
    
    private static boolean menuOperaciones() {
        System.out.println("\nMENÚ 2: OPERACIONES");
        System.out.println("a) Crear tablas");
        System.out.println("b) Insertar alumno");
        System.out.println("c) Insertar curso");
        System.out.println("d) Actualizar alumno");
        System.out.println("e) Actualizar curso");
        System.out.println("f) Listar alumnos");
        System.out.println("g) Listar alumnos con cursos");
        System.out.println("h) Buscar alumno por ID");
        System.out.println("0) Salir");
        System.out.print("\nOpción: ");
        
        String opcion = sc.nextLine().toLowerCase();
        
        switch(opcion) {
            case "a": dao.crearTablas(); break;
            case "b": insertarAlumno(); break;
            case "c": insertarCurso(); break;
            case "d": actualizarAlumno(); break;
            case "e": actualizarCurso(); break;
            case "f": dao.listarAlumnos(); break;
            case "g": dao.listarAlumnosConCursos(); break;
            case "h": buscarAlumno(); break;
            case "0": return false;
            default: System.out.println("Opción inválida");
        }
        return true;
    }
    
    private static void insertarAlumno() {
    dao.insertarAlumno(new Alumno(1, "Alvaro Balas", 19));
    System.out.println("Alumno Álvaro Balas insertado");
    }

    private static void insertarCurso() {
        dao.insertarCurso(new Curso(1, "Acceso a Datos", 1));
        System.out.println("Curso 'Acceso a Datos' insertado para Álvaro Balas");
    }

    private static void actualizarAlumno() {
        dao.actualizarAlumno(new Alumno(1, "Alvaro Balas Jimenez", 20));
        System.out.println("Alumno actualizado");
    }

    private static void actualizarCurso() {
        dao.actualizarCurso(new Curso(1, "Programación Multimedia", 1));
        System.out.println("Curso actualizado");
    }

    private static void buscarAlumno() {
        dao.buscarAlumnoPorId(1);
    }
    
    private static int leerInt() {
        while (true) {
            try {
                int num = Integer.parseInt(sc.nextLine());
                return num;
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Intenta de nuevo: ");
            }
        }
    }
}
