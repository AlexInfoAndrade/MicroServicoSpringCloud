package com.dio.alexander.catalogoproduto.repository;

import com.dio.alexander.catalogoproduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
