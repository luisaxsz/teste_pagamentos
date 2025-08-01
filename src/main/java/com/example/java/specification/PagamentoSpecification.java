package com.example.java.specification;

import com.example.java.domain.model.Pagamento;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PagamentoSpecification {

  public static Specification<Pagamento> filtroGeral(String q) {
    return (root, query, builder) -> {
      if (q == null || q.isEmpty()) {
        return builder.conjunction();
      }

      String filtroLike = "%" + q.toLowerCase() + "%";

      return builder.or(
        builder.like(builder.lower(root.get("cpfCnpj")), filtroLike),
        builder.like(builder.lower(root.get("numCartao")), filtroLike),
        builder.like(builder.lower(root.get("tipo").get("nome")), filtroLike),
        builder.like(builder.lower(root.get("status").get("nome")), filtroLike)
      );
    };
  }


}
