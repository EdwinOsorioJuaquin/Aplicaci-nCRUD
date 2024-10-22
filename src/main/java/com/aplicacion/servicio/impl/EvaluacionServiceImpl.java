package com.aplicacion.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicacion.dominio.Evaluacion;
import com.aplicacion.repositorio.EvaluacionRepository;
import com.aplicacion.servicio.EvaluacionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Transactional
    @Override
    public Evaluacion agregar(Evaluacion entidad) {
        return evaluacionRepository.save(entidad);
    }

    @Override
    public List<Evaluacion> listarTodos() {
        return evaluacionRepository.findAll();
    }

    @Override
    public Optional<Evaluacion> buscar(Long id) {
        return evaluacionRepository.findById(id);
    }

    @Transactional
    @Override
    public Evaluacion actualizar(Evaluacion entidad) {
        if (evaluacionRepository.existsById(entidad.getId())) {
            return evaluacionRepository.save(entidad);
        } else {
            throw new EntityNotFoundException("Evaluacion no encontrada con ID: " + entidad.getId());
        }
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if (evaluacionRepository.existsById(id)) {
            evaluacionRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Evaluacion no encontrada con ID: " + id);
        }
    }
}
