package com.dio.alexander.catalogoproduto.mapper;

import com.dio.alexander.catalogoproduto.dto.request.ProdutoDTO;
import com.dio.alexander.catalogoproduto.model.Produto;
import org.mapstruct.factory.Mappers;

public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto toModel(ProdutoDTO produtoDTO);
    ProdutoDTO toDTO(Produto produto);
}
