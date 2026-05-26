package com.quiz1.app.repositorio;

import com.quiz1.app.models.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeticionRepositorio extends JpaRepository<Competicion, Long> { }
