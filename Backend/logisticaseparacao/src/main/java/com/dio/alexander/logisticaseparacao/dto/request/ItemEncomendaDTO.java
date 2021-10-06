package com.dio.alexander.logisticaseparacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEncomendaDTO implements Serializable {

    private Long id;
    private Long produtoId;
    private Double quantidade;
}
