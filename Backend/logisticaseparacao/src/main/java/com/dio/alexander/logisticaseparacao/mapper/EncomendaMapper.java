package com.dio.alexander.logisticaseparacao.mapper;

import com.dio.alexander.logisticaseparacao.dto.request.EncomendaDTO;
import com.dio.alexander.logisticaseparacao.model.Encomenda;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface EncomendaMapper {

    Encomenda toModel(EncomendaDTO carrinhoDTO);
    EncomendaDTO toDTO(Encomenda carrinho);
}
