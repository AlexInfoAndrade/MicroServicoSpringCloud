package com.dio.alexander.pagamento.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO implements Serializable {

    private Long id;
    private String carrinhoCode;
    private String code;
    private Double valor;
    private String formaPagamento;
    private String status;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAlteracao;
}
