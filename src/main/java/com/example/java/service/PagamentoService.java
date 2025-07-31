package com.example.java.service;

import com.example.java.domain.model.Pagamento;
import com.example.java.repository.PagamentoRepository;
import com.example.java.repository.StatusPagamentoRepository;
import com.example.java.repository.TipoPagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.java.domain.enums.StatusEnum.PENDENTE_PROCESSAMENTO;

@Service
@AllArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final StatusPagamentoRepository statusPagamentoRepository;
    private final TipoPagamentoRepository tipoPagamentoRepository;
    private final ModelMapper modelMapper;

    public List<Pagamento> listar(Pageable pageable) {
        return pagamentoRepository.findAll(pageable).getContent();
    }

    public Pagamento adicionar(Pagamento resource){
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
}
