package com.dio.alexander.carrinho.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrinhoDTO {

    private Long id;
    private Long produtoId;
    private Double valor;
    private Double quantidade;
}
