package com.dio.alexander.carrinho.service;

import com.dio.alexander.carrinho.dto.request.CarrinhoDTO;
import com.dio.alexander.carrinho.dto.request.ItemCarrinhoDTO;
import com.dio.alexander.carrinho.dto.response.MessageResponseDTO;
import com.dio.alexander.carrinho.exception.CarrinhoNotFoundException;
import com.dio.alexander.carrinho.exception.ItemCarrinhoNotFoundException;

import java.util.List;

public interface CarrinhoService {

    MessageResponseDTO createCarrinho(CarrinhoDTO carrinhoDTO);

    CarrinhoDTO pagar(String code) throws CarrinhoNotFoundException;

    CarrinhoDTO carrinhoByCode(String code) throws CarrinhoNotFoundException;

    MessageResponseDTO updateCarrinho(String code, CarrinhoDTO carrinhoDTO)
            throws CarrinhoNotFoundException;

    void deleteCarrinho(String code) throws CarrinhoNotFoundException;

    MessageResponseDTO createItemCarrinho(String carrinhoCode, ItemCarrinhoDTO itemCarrinhoDTO)
            throws CarrinhoNotFoundException;

    List<ItemCarrinhoDTO> allItemCarrinhos(String carrinhoCode) throws CarrinhoNotFoundException;

    MessageResponseDTO updateItemCarrinho(String carrinhoCode, Long id, ItemCarrinhoDTO itemCarrinhoDTO)
            throws ItemCarrinhoNotFoundException;

    void deleteItemCarrinho(String carrinhoCode, Long id) throws ItemCarrinhoNotFoundException;
}
