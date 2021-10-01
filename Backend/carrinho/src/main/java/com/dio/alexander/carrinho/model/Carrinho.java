package com.dio.alexander.carrinho.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<ItemCarrinho> itens;

    @Column
    @CreatedDate
    private LocalDateTime dataInclusao;

    @Column
    @LastModifiedDate
    private LocalDateTime dataAlteracao;

    public enum Status {
        CRIADO,
        PENDENTE,
        APROVADO
    }
}


