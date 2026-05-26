package com.quiz1.app.repositorio;

import com.quiz1.app.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepositorio extends JpaRepository<Club, Long> { }
