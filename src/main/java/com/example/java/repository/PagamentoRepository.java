package com.example.java.repository;

import com.example.java.domain.model.Pagamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Page<Pagamento> findAll(Pageable pageable);
}
