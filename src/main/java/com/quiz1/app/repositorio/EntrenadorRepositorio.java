package com.quiz1.app.repositorio;

import com.quiz1.app.models.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepositorio extends JpaRepository<Entrenador, Long> { }
