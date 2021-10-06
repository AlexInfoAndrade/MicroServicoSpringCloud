package com.dio.alexander.carrinho.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrinhoDTO implements Serializable {

    private Long id;
    private Long produtoId;
    private Double valor;
    private Double quantidade;
}
