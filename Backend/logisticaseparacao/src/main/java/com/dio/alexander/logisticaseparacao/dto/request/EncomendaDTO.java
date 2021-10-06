package com.dio.alexander.logisticaseparacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncomendaDTO implements Serializable {

    private Long id;
    private String pagamentoCode;
    private String carrinhoCode;
    private String code;
    private String status;
    private List<ItemEncomendaDTO> itens;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAlteracao;
}
