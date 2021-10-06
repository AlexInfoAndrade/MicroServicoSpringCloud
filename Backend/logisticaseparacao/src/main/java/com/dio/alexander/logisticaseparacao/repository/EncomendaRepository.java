package com.dio.alexander.logisticaseparacao.repository;

import com.dio.alexander.logisticaseparacao.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {

    Optional<Encomenda> findByCode(String code);
}
