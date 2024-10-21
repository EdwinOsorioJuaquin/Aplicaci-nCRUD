package com.aplicacion.repositorio;

import com.aplicacion.modelo.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepositorio extends JpaRepository<Classroom, Long> {
}
