package com.aplicacion.service;

import com.aplicacion.modelo.Evaluacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacion.repositorio.EvaluacionRepositorio;

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;

    public List<Evaluacion> listar() {
        return evaluacionRepositorio.findAll();
    }

    public Evaluacion guardar(Evaluacion evaluacion) {
        return evaluacionRepositorio.save(evaluacion);
    }

    public Optional<Evaluacion> obtenerPorId(Long id) {
        return evaluacionRepositorio.findById(id);
    }

    public void eliminar(Long id) {
        evaluacionRepositorio.deleteById(id);
    }
}
