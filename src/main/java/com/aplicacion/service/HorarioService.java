package com.aplicacion.service;

import com.aplicacion.modelo.Horario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacion.repositorio.HorarioRepositorio;


@Service
public class HorarioService {
    @Autowired
    private HorarioRepositorio horarioRepositorio;

    public List<Horario> listar() {
        return horarioRepositorio.findAll();
    }

    public Horario guardar(Horario horario) {
        return horarioRepositorio.save(horario);
    }

    public Optional<Horario> obtenerPorId(Long id) {
        return horarioRepositorio.findById(id);
    }

    public void eliminar(Long id) {
        horarioRepositorio.deleteById(id);
    }
}
