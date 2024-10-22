package com.aplicacion.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicacion.dominio.Docente;
import com.aplicacion.repositorio.DocenteRepository;
import com.aplicacion.servicio.DocenteService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Transactional
    @Override
    public Docente agregar(Docente entidad) {
        return docenteRepository.save(entidad);
    }

    @Override
    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    @Override
    public Optional<Docente> buscar(Long id) {
        return docenteRepository.findById(id);
    }

    @Transactional
    @Override
    public Docente actualizar(Docente entidad) {
        if (docenteRepository.existsById(entidad.getId())) {
            return docenteRepository.save(entidad);
        } else {
            throw new EntityNotFoundException("Docente no encontrado con ID: " + entidad.getId());
        }
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if (docenteRepository.existsById(id)) {
            docenteRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Docente no encontrado con ID: " + id);
        }
    }
}

