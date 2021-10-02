package com.dio.alexander.carrinho.mapper;

import com.dio.alexander.carrinho.dto.request.CarrinhoDTO;
import com.dio.alexander.carrinho.model.Carrinho;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CarrinhoMapper {

    Carrinho toModel(CarrinhoDTO carrinhoDTO);
    CarrinhoDTO toDTO(Carrinho carrinho);
}
