package com.dio.alexander.catalogoproduto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

    private Long id;

    @NotEmpty
    @Size(min=3, max=150)
    private String descricao;

    private Double estoque;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
    private Double preco;
    private boolean ativo;
}
