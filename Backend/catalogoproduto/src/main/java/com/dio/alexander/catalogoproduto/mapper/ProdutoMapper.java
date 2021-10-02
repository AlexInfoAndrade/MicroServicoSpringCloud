package com.dio.alexander.catalogoproduto.mapper;

import com.dio.alexander.catalogoproduto.dto.request.ProdutoDTO;
import com.dio.alexander.catalogoproduto.model.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ProdutoMapper {

    Produto toModel(ProdutoDTO produtoDTO);
    ProdutoDTO toDTO(Produto produto);
}
