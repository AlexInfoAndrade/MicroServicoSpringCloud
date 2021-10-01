package com.dio.alexander.carrinho.mapper;

import com.dio.alexander.carrinho.dto.request.ItemCarrinhoDTO;
import com.dio.alexander.carrinho.model.ItemCarrinho;
import org.mapstruct.factory.Mappers;

public interface ItemCarrinhoMapper {

    ItemCarrinhoMapper INSTANCE = Mappers.getMapper(ItemCarrinhoMapper.class);

    ItemCarrinho toModel(ItemCarrinhoDTO produtoDTO);
    ItemCarrinhoDTO toDTO(ItemCarrinho produto);
}
