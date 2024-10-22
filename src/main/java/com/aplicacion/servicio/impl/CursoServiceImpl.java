package com.aplicacion.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicacion.dominio.Curso;
import com.aplicacion.repositorio.CursoRepository;
import com.aplicacion.servicio.CursoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @Override
    public Curso agregar(Curso entidad) {
        return cursoRepository.save(entidad);
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> buscar(Long id) {
        return cursoRepository.findById(id);
    }

    @Transactional
    @Override
    public Curso actualizar(Curso entidad) {
        if (cursoRepository.existsById(entidad.getId())) {
            return cursoRepository.save(entidad);
        } else {
            throw new EntityNotFoundException("Curso no encontrado con ID: " + entidad.getId());
        }
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Curso no encontrado con ID: " + id);
        }
    }
}
