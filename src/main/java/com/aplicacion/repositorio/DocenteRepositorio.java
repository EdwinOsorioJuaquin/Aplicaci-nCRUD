package com.aplicacion.repositorio;

import com.aplicacion.modelo.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepositorio extends JpaRepository<Docente, Long> {
}

