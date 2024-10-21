package com.aplicacion.service;

import com.aplicacion.modelo.Classroom;
import com.aplicacion.repositorio.ClassroomRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServicio {

    @Autowired
    private ClassroomRepositorio classroomRepositorio;

    public List<Classroom> findAll() {
        return classroomRepositorio.findAll();
    }

    public Optional<Classroom> findById(Long id) {
        return classroomRepositorio.findById(id);
    }

    public Classroom save(Classroom classroom) {
        return classroomRepositorio.save(classroom);
    }

    public Classroom update(Long id, Classroom classroomDetails) {
        Optional<Classroom> optionalClassroom = classroomRepositorio.findById(id);
        if (optionalClassroom.isPresent()) {
            Classroom classroom = optionalClassroom.get();
            classroom.setCodigo(classroomDetails.getCodigo());
            classroom.setCapacidad(classroomDetails.getCapacidad());
            classroom.setUbicacion(classroomDetails.getUbicacion());
            classroom.setDocenteAsignado(classroomDetails.getDocenteAsignado());
            return classroomRepositorio.save(classroom);
        } else {
            throw new RuntimeException("Classroom no encontrado: " + id);
        }
    }

    public void deleteById(Long id) {
        classroomRepositorio.deleteById(id);
    }
}
