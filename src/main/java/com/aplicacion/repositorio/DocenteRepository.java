package com.aplicacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.dominio.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
}
