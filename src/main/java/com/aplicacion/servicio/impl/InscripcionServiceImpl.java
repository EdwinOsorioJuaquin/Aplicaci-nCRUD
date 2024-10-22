package com.aplicacion.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicacion.dominio.Inscripcion;
import com.aplicacion.repositorio.InscripcionRepository;
import com.aplicacion.servicio.InscripcionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Transactional
    @Override
    public Inscripcion agregar(Inscripcion entidad) {
        return inscripcionRepository.save(entidad);
    }

    @Override
    public List<Inscripcion> listarTodos() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Optional<Inscripcion> buscar(Long id) {
        return inscripcionRepository.findById(id);
    }

    @Transactional
    @Override
    public Inscripcion actualizar(Inscripcion entidad) {
        if (inscripcionRepository.existsById(entidad.getId())) {
            return inscripcionRepository.save(entidad);
        } else {
            throw new EntityNotFoundException("Inscripción no encontrada con ID: " + entidad.getId());
        }
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Inscripción no encontrada con ID: " + id);
        }
    }
}
