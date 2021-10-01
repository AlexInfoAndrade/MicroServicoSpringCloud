package com.dio.alexander.carrinho.repository;

import com.dio.alexander.carrinho.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Optional<Carrinho> findByCode(String code);
}
