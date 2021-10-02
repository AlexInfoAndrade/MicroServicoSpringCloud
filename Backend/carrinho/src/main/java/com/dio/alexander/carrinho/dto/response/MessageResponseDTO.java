package com.dio.alexander.carrinho.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    private Long id;
    private String code;
    private String message;
}
