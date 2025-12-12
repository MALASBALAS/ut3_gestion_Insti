# Sistema de Gestión de Hosting - UT3

## Descripción del Proyecto

Sistema de gestión de Servidor y Webs que implementa el patrón DAO con tres tipos de conexión:
- **Mock**: Simulación en memoria sin base de datos
- **SQLite**: Base de datos local ligera
- **Oracle**: Base de datos empresarial

## Estructura del Proyecto

```
src/
├── model/
│   ├── Servidor.java     (Entidad Servidor)
│   └── Web.java      (Entidad Web con FK a Servidor)
├── dao/
│   ├── HostingDAO.java         (Interfaz DAO)
│   ├── MockHostingDAO.java     (Implementación Mock)
│   ├── SQLiteHostingDAO.java   (Implementación SQLite)
│   └── OracleHostingDAO.java   (Implementación Oracle)
└── main/
    └── Main.java                 (Menús y ejecución)
```

## Base de Datos

**Tabla 1: Servidor**
- id_Servidor (PK)
- nombre
- edad

**Tabla 2: WebS**
- id_Web (PK)
- nombre
- id_Servidor (FK → Servidor)

## Cómo Compilar y Ejecutar

### Compilar manualmente

```powershell
# Compilar
javac -d bin -sourcepath src src/main/Main.java

# Ejecutar
java -cp bin main.Main
```

## Funcionalidades

**Menú 1 - Tipo de Conexión:**
1. Mock
2. SQLite
3. Oracle

**Menú 2 - Operaciones:**
- a) Crear tablas
- b) Insertar Servidor (Tabla 1)
- c) Insertar Web (Tabla 2)
- d) Actualizar Servidor (Tabla 1)
- e) Actualizar Web (Tabla 2)
- f) Listar todos los Servidor
- g) Listar Servidor con sus Webs (relación FK)
- h) Buscar Servidor por ID (consulta con parámetro)
- 0) Salir

---
