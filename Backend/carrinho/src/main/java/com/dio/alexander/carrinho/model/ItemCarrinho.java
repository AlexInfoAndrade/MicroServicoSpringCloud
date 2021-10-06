package com.dio.alexander.carrinho.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinho implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long produtoId;

    @Column
    private Double valor;

    @Column
    private Double quantidade;

    @ManyToOne
    private Carrinho carrinho;
}
