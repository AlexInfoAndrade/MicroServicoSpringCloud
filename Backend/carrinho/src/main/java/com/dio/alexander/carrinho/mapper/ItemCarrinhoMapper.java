package com.dio.alexander.carrinho.mapper;

import com.dio.alexander.carrinho.dto.request.ItemCarrinhoDTO;
import com.dio.alexander.carrinho.model.ItemCarrinho;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface ItemCarrinhoMapper {

    ItemCarrinho toModel(ItemCarrinhoDTO itemCarrinhoDTO);
    ItemCarrinhoDTO toDTO(ItemCarrinho itemCarrinho);
}
