package com.aplicacion.service;

import com.aplicacion.modelo.Curso;
import com.aplicacion.repositorio.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServicio {

    @Autowired
    private CursoRepositorio cursoRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso update(Long id, Curso cursoDetails) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            curso.setNombre(cursoDetails.getNombre());
            curso.setDescripcion(cursoDetails.getDescripcion());
            curso.setDocente(cursoDetails.getDocente());
            return cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Curso no encontrado: " + id);
        }
    }

    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }
    
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }
}

