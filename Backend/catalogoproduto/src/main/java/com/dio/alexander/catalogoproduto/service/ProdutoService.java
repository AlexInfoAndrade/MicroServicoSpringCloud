package com.dio.alexander.catalogoproduto.service;

import com.dio.alexander.catalogoproduto.dto.request.ProdutoDTO;
import com.dio.alexander.catalogoproduto.dto.response.MessageResponseDTO;
import com.dio.alexander.catalogoproduto.exception.ProdutoNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    MessageResponseDTO createProduto(ProdutoDTO produtoDTO);

    ProdutoDTO produtoById(Long id) throws ProdutoNotFoundException;

    List<ProdutoDTO> allProdutos();

    MessageResponseDTO updateProduto(Long id, ProdutoDTO produtoDTO) throws ProdutoNotFoundException;

    void deleteProduto(Long id) throws ProdutoNotFoundException;
}
