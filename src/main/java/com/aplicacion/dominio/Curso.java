package com.aplicacion.dominio;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;
    
    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;
    
    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
    
    @ManyToMany
    @JoinTable(
        name = "curso_estudiante",  // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "curso_id"),  // Columna que referencia a Curso
        inverseJoinColumns = @JoinColumn(name = "estudiante_id")  // Columna que referencia a Alumno
    )
    private List<Estudiante> estudiantes;

    
    @OneToMany(mappedBy = "curso")
    private List<Evaluacion> evaluaciones;
    
    
}
