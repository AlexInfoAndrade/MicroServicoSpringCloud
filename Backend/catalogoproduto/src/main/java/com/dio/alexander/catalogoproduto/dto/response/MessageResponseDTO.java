package com.dio.alexander.catalogoproduto.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MessageResponseDTO implements Serializable {

    private Long id;
    private String message;
}
