package com.dio.alexander.carrinho.repository;

import com.dio.alexander.carrinho.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

    Optional<ItemCarrinho> findByCarrinhoCodeAndId(String carrinhoCode, Long aLong);
}
