package com.aplicacion.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="persona")
public class Persona {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private int id;
private String nombres;
private String telefono;

public Persona() {
	
}



public Persona(int id, String nombres, String telefono) {
	super();
	this.id = id;
	this.nombres = nombres;
	this.telefono = telefono;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNombres() {
	return nombres;
}
public void setNombres(String nombres) {
	this.nombres = nombres;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
}
