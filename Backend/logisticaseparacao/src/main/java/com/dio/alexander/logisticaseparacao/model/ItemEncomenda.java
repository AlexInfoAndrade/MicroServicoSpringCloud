package com.dio.alexander.logisticaseparacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEncomenda implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long produtoId;

    @Column
    private Double quantidade;

    @ManyToOne
    private Encomenda encomenda;
}
