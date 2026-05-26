package com.quiz1.app.repositorio;

import com.quiz1.app.models.Asociacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepositorio extends JpaRepository<Asociacion, Long> { }
