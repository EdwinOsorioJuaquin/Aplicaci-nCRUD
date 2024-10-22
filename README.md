### PRODUCTO DE ARQUITECTURA DE MICROSERVICIOS
# Sistema de Gestión Académica

## Descripción

Este proyecto es un **Sistema de Gestión Académica** desarrollado en Java utilizando **Spring Boot**, **Thymeleaf** como motor de plantillas y **JPA** para la persistencia de datos. El sistema permite gestionar entidades relacionadas con la administración académica como cursos, estudiantes, inscripciones, docentes, aulas, asignaturas, horarios, evaluaciones, y más.

## Características del Proyecto

- **CRUD completo** para las entidades:
  - Estudiantes
  - Cursos
  - Inscripciones
  - Docentes
  - Aulas
  - Asignaturas
  - Evaluaciones
  - Horarios
- Validaciones de datos en formularios utilizando **Thymeleaf** y **Hibernate Validator**.
- Uso de relaciones entre entidades utilizando **JPA** (Many-to-One, One-to-Many, Many-to-Many).
- Uso de **Bootstrap** para el diseño de las vistas.
- Gestión de errores y mensajes de retroalimentación para el usuario.

## Requisitos Previos

Para ejecutar el proyecto localmente, asegúrate de tener instalado:

- **Java 22** o superior
- **Maven**
- **PostgreSQL** (u otro gestor de base de datos)

## Configuración del Proyecto

1. Clona el repositorio del proyecto:

    ```bash
    git clone https://github.com/edwinosoriojuaquin/aplicacionCRUD.git
    cd aplicacionCRUD
    ```

2. Crea la base de datos en MySQL o en el gestor de base de datos de tu preferencia:

    ```sql
    CREATE DATABASE neondb;
    ```

3. Configura el archivo `application.properties` o `application.yml` con los detalles de tu conexión a la base de datos:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/neondb
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Instala las dependencias del proyecto utilizando Maven:

    ```bash
    mvn clean install
    ```

5. Ejecuta la aplicación:

    ```bash
    mvn spring-boot:run
    ```

6. Abre tu navegador y accede a la aplicación en:

    ```bash
    http://localhost:8080
    ```

## Estructura del Proyecto

El proyecto sigue una estructura típica de **Spring Boot**, organizada en diferentes paquetes:

- `com.aplicacion.dominio`: Clases de las entidades del modelo.
- `com.aplicacion.repositorio`: Interfaces de los repositorios de JPA.
- `com.aplicacion.servicio`: Interfaces de los servicios que manejan la lógica de negocio.
- `com.aplicacion.servicio.impl`: Implementaciones de los servicios.
- `com.aplicacion.controlador`: Controladores para manejar las rutas y solicitudes HTTP.
- `resources/templates`: Vistas Thymeleaf para cada entidad.
- `resources/static`: Archivos estáticos como CSS y JS.

## Endpoints Principales

| Entidad      | Ruta Base            | Funcionalidad                             |
| ------------ | -------------------- | ----------------------------------------- |
| Estudiantes  | `/estudiante`         | Gestión de estudiantes (crear, editar, eliminar, listar) |
| Cursos       | `/curso`              | Gestión de cursos                         |
| Inscripciones| `/inscripcion`        | Gestión de inscripciones                  |
| Docentes     | `/docente`            | Gestión de docentes                       |
| Aulas        | `/aula`               | Gestión de aulas                          |
| Asignaturas  | `/asignatura`         | Gestión de asignaturas                    |
| Evaluaciones | `/evaluacion`         | Gestión de evaluaciones                   |
| Horarios     | `/horario`            | Gestión de horarios                       |

## Integrantes del Equipo

1. **GONZALES BERROCAL LUIS** - _0202114015_
2. **LIZA GUERRERO PIERO** - _0202114037_
3. **LOMA AGUIRRE MARIESTHER** - _0202114038_
4. **LUJAN TRUJILLO ANDERSON** - _0202114039_
5. **OSORIO JUAQUIN EDWIN** - _0202114044_
5. **TORRES MILLA JOSE** - _0202114009_
6. **ZELADA PULIDO RODRIGP** - _0202114018_

## Contribución

Si deseas contribuir al proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva característica'`).
4. Sube los cambios a tu repositorio (`git push origin feature/nueva-caracteristica`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la licencia MIT. Para más detalles, revisa el archivo [LICENSE](LICENSE).

