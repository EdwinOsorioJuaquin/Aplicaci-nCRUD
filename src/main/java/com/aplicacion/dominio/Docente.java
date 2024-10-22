package com.aplicacion.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true) // Para incluir los atributos de la clase base en el toString
@Entity
public class Docente  extends Persona{

    private String especialidad;

    @OneToMany(mappedBy = "docente")
    private List<Curso> cursos;

    
    public Docente(Long id, String nombres, String apellidos, String telefono, String dni, String correo, String especialidad) {
        super(id, nombres, apellidos, telefono, dni, correo); // Aquí puedes pasar un ID (0, ya que se genera automáticamente)
        this.especialidad = especialidad;
    }
    
    public Docente() {
    }
}
