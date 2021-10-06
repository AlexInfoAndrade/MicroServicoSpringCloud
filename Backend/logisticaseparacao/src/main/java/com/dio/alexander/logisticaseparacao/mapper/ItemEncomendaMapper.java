package com.dio.alexander.logisticaseparacao.mapper;

import com.dio.alexander.logisticaseparacao.dto.request.ItemEncomendaDTO;
import com.dio.alexander.logisticaseparacao.model.ItemEncomenda;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ItemEncomendaMapper {

    ItemEncomenda toModel(ItemEncomendaDTO itemCarrinhoDTO);
    ItemEncomendaDTO toDTO(ItemEncomenda itemCarrinho);
}
