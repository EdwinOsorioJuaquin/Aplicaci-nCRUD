package com.aplicacion.dominio;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true) // Para incluir los atributos de la clase base en el toString
@Entity
public class Estudiante extends Persona {
	private String nivel;
	
	@OneToMany(mappedBy = "estudiante")
	private List<Evaluacion> evaluaciones;
	
	@OneToMany(mappedBy = "estudiante")
	private List<Inscripcion> inscripciones;
	
	
	// Relación muchos a muchos con Curso
    @ManyToMany(mappedBy = "estudiantes")  // Inverso de la relación
    private List<Curso> cursos;
    
    
	
	public Estudiante(Long id, String nombres, String apellidos, String telefono, String dni, String correo, String nivel) {
        super(id, nombres, apellidos, telefono, dni, correo); // Aquí puedes pasar un ID (0, ya que se genera automáticamente)
        this.nivel = nivel;
    }
	
	public Estudiante () {
		
	}
}
