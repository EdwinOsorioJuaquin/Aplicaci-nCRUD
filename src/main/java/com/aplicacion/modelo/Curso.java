package com.aplicacion.modelo;

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
    private String apellido;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
    
    @OneToMany(mappedBy = "curso")
    private List<Evaluacion> evaluaciones;
}
