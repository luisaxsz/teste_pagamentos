package com.example.java.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAGAMENTO")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAGAMENTO")
    @SequenceGenerator(name = "SEQ_PAGAMENTO", sequenceName = "SEQ_PAGAMENTO", allocationSize = 1)
    private Long id;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @ManyToOne
    @JoinColumn(name = "TIPO_PAGAMENTO_ID", nullable = false)
    private TipoPagamento tipo;

    @Column(name = "NUM_CARTAO")
    private String numCartao;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID", nullable = false)
    private StatusPagamento status;

    @Column(name = "ATIVO")
    private Boolean ativo;

}
