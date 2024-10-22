package com.aplicacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.dominio.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
