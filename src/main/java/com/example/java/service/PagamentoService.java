package com.example.java.service;

import com.example.java.domain.model.Pagamento;
import com.example.java.repository.PagamentoRepository;
import com.example.java.repository.StatusPagamentoRepository;
import com.example.java.repository.TipoPagamentoRepository;
import com.example.java.specification.PagamentoSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.example.java.domain.enums.StatusEnum.PENDENTE_PROCESSAMENTO;

@Service
@AllArgsConstructor
public class PagamentoService {

  private final PagamentoRepository pagamentoRepository;
  private final StatusPagamentoRepository statusPagamentoRepository;
  private final TipoPagamentoRepository tipoPagamentoRepository;
  private final ModelMapper modelMapper;
  private final PagamentoSpecification pagamentoSpecification;

  public Pagamento adicionar(Pagamento resource) {
    var tipoPagamento = tipoPagamentoRepository.findById(resource.getTipo().getId()).orElseThrow(EntityNotFoundException::new);
    var statusPagamento = statusPagamentoRepository.findStatusPagamentoByTipo(PENDENTE_PROCESSAMENTO);
    resource.setStatus(statusPagamento);
    resource.setTipo(tipoPagamento);
    return pagamentoRepository.save(resource);
  }

  public Pagamento inativar(Long id) {
    var pagamento = pagamentoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    pagamento.setAtivo(false);
    return pagamentoRepository.save(pagamento);
  }

  public Pagamento atualizarStatus(Long id, Pagamento resource) {
    var statusPagamento = statusPagamentoRepository.findById(resource.getStatus().getId()).orElseThrow(EntityNotFoundException::new);
    resource.setStatus(statusPagamento);
    return pagamentoRepository.save(resource);
  }

  public Page<Pagamento> listar(
    int pagina,
    int tamanho,
    String q) {
    Pageable pageable = PageRequest.of(pagina, tamanho);
    Specification<Pagamento> spec = montarSpecification(q);

    return pagamentoRepository.findAll(spec, pageable);
  }


  public Specification<Pagamento> montarSpecification(String q) {
    Specification<Pagamento> spec = Specification.where(null);

    if (q != null && !q.isEmpty()) {
      spec = spec.and(PagamentoSpecification.filtroGeral(q));
    }

    return spec;
  }

}
