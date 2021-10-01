package com.dio.alexander.carrinho.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoDTO {

    private Long id;
    private String status;
    private List<ItemCarrinhoDTO> itens;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAlteracao;

}
