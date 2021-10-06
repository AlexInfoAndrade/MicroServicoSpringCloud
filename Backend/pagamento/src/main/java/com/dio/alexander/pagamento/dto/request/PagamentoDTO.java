package com.dio.alexander.pagamento.dto.request;

import com.dio.alexander.pagamento.model.Pagamento;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

public class PagamentoDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String carrinhoCode;
    private String code;
    private Double valor;
    private String formaPagamento;
    private String status;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAlteracao;
}
