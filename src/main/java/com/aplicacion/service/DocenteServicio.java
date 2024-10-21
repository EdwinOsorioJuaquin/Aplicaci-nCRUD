package com.aplicacion.service;

import com.aplicacion.modelo.Docente;
import com.aplicacion.repositorio.DocenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteServicio {

    @Autowired
    private DocenteRepositorio docenteRepository;

    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    public Optional<Docente> findById(Long id) {
        return docenteRepository.findById(id);
    }

    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    public Docente update(Long id, Docente docenteDetails) {
        Optional<Docente> optionalDocente = docenteRepository.findById(id);
        if (optionalDocente.isPresent()) {
            Docente docente = optionalDocente.get();
            docente.setNombre(docenteDetails.getNombre());
            docente.setApellido(docenteDetails.getApellido());
            docente.setCorreo(docenteDetails.getCorreo());
            return docenteRepository.save(docente);
        } else {
            throw new RuntimeException("Docente no encontrado: " + id);
        }
    }

    public void deleteById(Long id) {
        docenteRepository.deleteById(id);
    }
}

