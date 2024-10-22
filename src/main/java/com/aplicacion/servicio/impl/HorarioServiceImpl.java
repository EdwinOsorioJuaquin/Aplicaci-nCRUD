package com.aplicacion.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicacion.dominio.Horario;
import com.aplicacion.repositorio.HorarioRepository;
import com.aplicacion.servicio.HorarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Transactional
    @Override
    public Horario agregar(Horario entidad) {
        return horarioRepository.save(entidad);
    }

    @Override
    public List<Horario> listarTodos() {
        return horarioRepository.findAll();
    }

    @Override
    public Optional<Horario> buscar(Long id) {
        return horarioRepository.findById(id);
    }

    @Transactional
    @Override
    public Horario actualizar(Horario entidad) {
        if (horarioRepository.existsById(entidad.getId())) {
            return horarioRepository.save(entidad);
        } else {
            throw new EntityNotFoundException("Horario no encontrado con ID: " + entidad.getId());
        }
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if (horarioRepository.existsById(id)) {
            horarioRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Horario no encontrado con ID: " + id);
        }
    }
}

