package com.dio.alexander.pagamento.repository;

import com.dio.alexander.pagamento.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findByCode(String code);
}
