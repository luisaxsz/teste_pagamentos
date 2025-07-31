package com.example.java.domain.model;

import com.example.java.domain.enums.TipoPagamentoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TIPO_PAGAMENTO")
public class TipoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_PAGAMENTO")
    @SequenceGenerator(name = "SEQ_TIPO_PAGAMENTO", sequenceName = "SEQ_TIPO_PAGAMENTO", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO")
    private TipoPagamentoEnum tipo;

    @Column(name = "NOME")
    private String nome;
}
