package com.aplicacion.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.modelo.Persona;

@Repository
public interface IPersona extends CrudRepository<Persona,Integer>{

}
