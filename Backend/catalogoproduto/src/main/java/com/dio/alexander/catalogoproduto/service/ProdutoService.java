package com.dio.alexander.catalogoproduto.service;

import com.dio.alexander.catalogoproduto.dto.request.ProdutoDTO;
import com.dio.alexander.catalogoproduto.dto.response.MessageResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    MessageResponseDTO createProduto(ProdutoDTO produtoDTO);

    Optional<ProdutoDTO> produtoById(Long id);

    List<ProdutoDTO> allProdutos();

    MessageResponseDTO updateProduto(ProdutoDTO produtoDTO);

    void deleteProduto(Long id);
}
