package com.dio.alexander.pagamento.mapper;

import com.dio.alexander.pagamento.dto.request.PagamentoDTO;
import com.dio.alexander.pagamento.model.Pagamento;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PagamentoMapper {

    Pagamento toModel(PagamentoDTO pagamentoDTO);
    PagamentoDTO toDTO(Pagamento pagamento);
}
