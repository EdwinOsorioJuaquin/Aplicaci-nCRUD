package com.aplicacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.dominio.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
