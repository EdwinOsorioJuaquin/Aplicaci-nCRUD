package com.aplicacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.dominio.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
}
