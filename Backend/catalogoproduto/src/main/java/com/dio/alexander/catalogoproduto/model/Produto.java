package com.dio.alexander.catalogoproduto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Produto {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String descricao;

    @Column
    private Double estoque;

    @Column
    @CreatedDate
    private LocalDateTime dataInclusao;

    @Column
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @Column
    private Double preco;

    @Column
    private boolean ativo;
}
