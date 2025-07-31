package com.example.java.repository;

import com.example.java.domain.enums.StatusEnum;
import com.example.java.domain.model.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPagamentoRepository extends JpaRepository<StatusPagamento, Long> {

    StatusPagamento findStatusPagamentoByTipo(StatusEnum tipo);
}
