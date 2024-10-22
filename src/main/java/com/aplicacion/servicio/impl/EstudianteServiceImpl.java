package com.aplicacion.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicacion.dominio.Estudiante;
import com.aplicacion.repositorio.EstudianteRepository;
import com.aplicacion.servicio.EstudianteService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

	 @Autowired
	    private EstudianteRepository estudianteRepository;
	 
	@Transactional
	@Override
	public Estudiante agregar(Estudiante entidad) {
		return estudianteRepository.save(entidad);
	}

	@Override
	public List<Estudiante> listarTodos() {
	    return estudianteRepository.findAll();
	}


	@Override
	public Optional<Estudiante> buscar(Long id) {
	    return estudianteRepository.findById(id);
	}


	@Transactional
	@Override
	public Estudiante actualizar(Estudiante entidad) {
	    if(estudianteRepository.existsById(entidad.getId())) {
	        return estudianteRepository.save(entidad);
	    } else {
	        throw new EntityNotFoundException("Estudiante no encontrado con ID: " + entidad.getId());
	    }
	}


	@Transactional
	@Override
	public void eliminar(Long id) {
	    if(estudianteRepository.existsById(id)) {
	        estudianteRepository.deleteById(id);
	    } else {
	        throw new EntityNotFoundException("Estudiante no encontrado con ID: " + id);
	    }
	}

	
}
