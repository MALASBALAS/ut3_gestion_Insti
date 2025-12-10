# Sistema de Gestión de Instituto - UT3

## Descripción del Proyecto

Sistema de gestión de alumnos y cursos que implementa el patrón DAO con tres tipos de conexión:
- **Mock**: Simulación en memoria sin base de datos
- **SQLite**: Base de datos local ligera
- **Oracle**: Base de datos empresarial

## Estructura del Proyecto

```
src/
├── model/
│   ├── Alumno.java     (Entidad alumno)
│   └── Curso.java      (Entidad curso con FK a alumno)
├── dao/
│   ├── InstitutoDAO.java         (Interfaz DAO)
│   ├── MockInstitutoDAO.java     (Implementación Mock)
│   ├── SQLiteInstitutoDAO.java   (Implementación SQLite)
│   └── OracleInstitutoDAO.java   (Implementación Oracle)
└── main/
    └── Main.java                 (Menús y ejecución)
```

## Base de Datos

**Tabla 1: ALUMNOS**
- id_alumno (PK)
- nombre
- edad

**Tabla 2: CURSOS**
- id_curso (PK)
- nombre
- id_alumno (FK → ALUMNOS)

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
- b) Insertar alumno (Tabla 1)
- c) Insertar curso (Tabla 2)
- d) Actualizar alumno (Tabla 1)
- e) Actualizar curso (Tabla 2)
- f) Listar todos los alumnos
- g) Listar alumnos con sus cursos (relación FK)
- h) Buscar alumno por ID (consulta con parámetro)
- 0) Salir

---
