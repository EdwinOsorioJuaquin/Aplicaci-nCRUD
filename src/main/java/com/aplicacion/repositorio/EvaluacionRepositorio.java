package com.aplicacion.repositorio;

import com.aplicacion.modelo.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepositorio extends JpaRepository<Evaluacion, Long> {
}
