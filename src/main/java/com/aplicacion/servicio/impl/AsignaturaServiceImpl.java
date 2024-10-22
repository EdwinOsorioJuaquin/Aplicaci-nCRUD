package com.aplicacion.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicacion.dominio.Asignatura;
import com.aplicacion.repositorio.AsignaturaRepository;
import com.aplicacion.servicio.AsignaturaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Transactional
    @Override
    public Asignatura agregar(Asignatura entidad) {
        return asignaturaRepository.save(entidad);
    }

    @Override
    public List<Asignatura> listarTodos() {
        return asignaturaRepository.findAll();
    }

    @Override
    public Optional<Asignatura> buscar(Long id) {
        return asignaturaRepository.findById(id);
    }

    @Transactional
    @Override
    public Asignatura actualizar(Asignatura entidad) {
        if (asignaturaRepository.existsById(entidad.getId())) {
            return asignaturaRepository.save(entidad);
        } else {
            throw new EntityNotFoundException("Asignatura no encontrada con ID: " + entidad.getId());
        }
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if (asignaturaRepository.existsById(id)) {
            asignaturaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Asignatura no encontrada con ID: " + id);
        }
    }
}
