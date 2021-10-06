package com.dio.alexander.pagamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String carrinhoCode;

    @Column
    private String code;

    @Column
    private Double valor;

    @Column
    private String formaPagamento;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @CreatedDate
    private LocalDateTime dataInclusao;

    @Column
    @LastModifiedDate
    private LocalDateTime dataAlteracao;

    public enum Status {
        PENDENTE,
        FINALIZADO
    }
}
