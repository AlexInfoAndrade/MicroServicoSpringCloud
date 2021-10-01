package com.dio.alexander.carrinho.mapper;

import com.dio.alexander.carrinho.dto.request.CarrinhoDTO;
import com.dio.alexander.carrinho.model.Carrinho;
import org.mapstruct.factory.Mappers;

public interface CarrinhoMapper {

    CarrinhoMapper INSTANCE = Mappers.getMapper(CarrinhoMapper.class);

    Carrinho toModel(CarrinhoDTO produtoDTO);
    CarrinhoDTO toDTO(Carrinho produto);
}
