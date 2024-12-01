package com.high.end.academia.academia.repository;

import com.high.end.academia.academia.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    List<Plano> findByAtivoFalse();

    Optional<Plano> findByAtivoTrue();
}