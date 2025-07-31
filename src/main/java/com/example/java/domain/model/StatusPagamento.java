package com.example.java.domain.model;

import com.example.java.domain.enums.StatusEnum;
import com.example.java.domain.enums.TipoPagamentoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "STATUS_PAGAMENTO")
public class StatusPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STATUS_PAGAMENTO")
    @SequenceGenerator(name = "SEQ_STATUS_PAGAMENTO", sequenceName = "SEQ_STATUS_PAGAMENTO", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO")
    private StatusEnum tipo;

    @Column(name = "NOME")
    private String nome;

}
