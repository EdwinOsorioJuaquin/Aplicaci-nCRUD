package com.aplicacion.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicacion.dominio.Aula;
import com.aplicacion.repositorio.AulaRepository;
import com.aplicacion.servicio.AulaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AulaServiceImpl implements AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Transactional
    @Override
    public Aula agregar(Aula entidad) {
        return aulaRepository.save(entidad);
    }

    @Override
    public List<Aula> listarTodos() {
        return aulaRepository.findAll();
    }

    @Override
    public Optional<Aula> buscar(Long id) {
        return aulaRepository.findById(id);
    }

    @Transactional
    @Override
    public Aula actualizar(Aula entidad) {
        if (aulaRepository.existsById(entidad.getId())) {
            return aulaRepository.save(entidad);
        } else {
            throw new EntityNotFoundException("Aula no encontrada con ID: " + entidad.getId());
        }
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if (aulaRepository.existsById(id)) {
            aulaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Aula no encontrada con ID: " + id);
        }
    }
}
