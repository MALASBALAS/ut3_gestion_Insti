package main;

import dao.*;
import java.util.Scanner;
import model.Servidor;
import model.Web;

// Sistema Hosting Principal
public class Main {
    
    private static final Scanner sc = new Scanner(System.in);
    private static HostingDAO dao;
    
    public static void main(String[] args) {
        System.out.println("\nSISTEMA GESTIÓN Hosting");
        System.out.println("2DAM - Acceso a Datos - UT3\n");
        
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
    // Menú BD
    private static HostingDAO seleccionarConexion() {
        System.out.println("MENÚ 1: TIPO DE CONEXIÓN");
        System.out.println("1. Mock (simulación)");
        System.out.println("2. SQLite");
        System.out.println("3. Oracle");
        System.out.println("0. Salir");
        System.out.print("\nOpción: ");
        
        int opcion = leerInt();
        
        return switch (opcion) {
            case 1 -> new MockHostingDAO();
            case 2 -> new SQLiteHostingDAO();
            case 3 -> new OracleHostingDAO();
            case 0 -> null;
            default -> {
                System.out.println("Opción inválida");
                yield seleccionarConexion();
            }
        };
    }


    // Menú operaciones
    private static boolean menuOperaciones() {
        System.out.println("\nMENÚ 2: OPERACIONES");
        System.out.println("a) Crear tablas");
        System.out.println("b) Insertar Servidor");
        System.out.println("c) Insertar Web");
        System.out.println("d) Actualizar Servidor");
        System.out.println("e) Actualizar Web");
        System.out.println("f) Listar Servidores");
        System.out.println("g) Listar Servidores con Webs");
        System.out.println("h) Buscar Servidor por ID");
        System.out.println("0) Salir");
        System.out.print("\nOpción: ");
        
        String opcion = sc.nextLine().toLowerCase();
        
        switch(opcion) {
            case "a" -> dao.crearTablas();
            case "b" -> insertarServidor();
            case "c" -> insertarWeb();
            case "d" -> actualizarServidor();
            case "e" -> actualizarWeb();
            case "f" -> dao.listarServidores();
            case "g" -> dao.listarServidoresConWebs();
            case "h" -> buscarServidor();
            case "0" -> { return false; }
            default -> System.out.println("Opción inválida");
        }
        return true;
    }
    

    //

    // Inserta servidor test
    private static void insertarServidor() {
        dao.insertarServidor(
            new Servidor(
                1,
                "IronMan-Server",
                "192.168.1.19",
                "Madrid - PC Jarvis"
            )
        );
        System.out.println("Servidor 'IronMan-Server' insertado.");
    }

    // Inserta web test
    private static void insertarWeb() {
        
        dao.insertarWeb(
            new Web(
                1,
                "balbe.xyz",
                "React / MariaDB",
                1
            )
        );
        System.out.println("Web 'balbe.xyz' insertada en IronMan-Server.");
    }

    // Actualiza servidor test
    private static void actualizarServidor() {
        
        dao.actualizarServidor(
            new Servidor(
                1,
                "IronMan-Server v2",
                "192.168.19.3",
                "Madrid - Cuarto técnico"
            )
        );
        System.out.println("Servidor actualizado correctamente.");
    }

    // Actualiza web test
    private static void actualizarWeb() {
        dao.actualizarWeb(
            new Web(
                1,
                "academyliquidity.com",
                "NodeJS / React",
                1
            )
        );
        System.out.println("Web actualizada correctamente.");
    }

    // Busca servidor test
    private static void buscarServidor() {
        dao.buscarServidorPorId(1);
    }

    // Lee entero
    private static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Intenta de nuevo: ");
            }
        }
    }
}
