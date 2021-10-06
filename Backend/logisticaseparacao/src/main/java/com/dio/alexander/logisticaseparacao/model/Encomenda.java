package com.dio.alexander.logisticaseparacao.model;

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
import java.util.List;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encomenda implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String pagamentoCode;

    @Column
    private String carrinhoCode;

    @Column
    private String code;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "encomenda", cascade = CascadeType.ALL)
    private List<ItemEncomenda> itens;

    @Column
    @CreatedDate
    private LocalDateTime dataInclusao;

    @Column
    @LastModifiedDate
    private LocalDateTime dataAlteracao;

    public enum Status {
        CRIADO,
        PENDENTE,
        ENTREGUE
    }
}
