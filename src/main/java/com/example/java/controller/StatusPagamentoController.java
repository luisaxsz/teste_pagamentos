package com.example.java.controller;

import com.example.java.domain.model.StatusPagamento;
import com.example.java.repository.StatusPagamentoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/status-pagamento")
@AllArgsConstructor
public class StatusPagamentoController {

    private final StatusPagamentoRepository statusPagamentoRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<StatusPagamento>> findAll() {
        return ResponseEntity.ok(statusPagamentoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StatusPagamento>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(statusPagamentoRepository.findById(id));
    }

    @PostMapping
    public StatusPagamento insert(@RequestBody StatusPagamento resource) {
        return statusPagamentoRepository.save(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusPagamento> update(@PathVariable Long id, @RequestBody StatusPagamento resource) {
        return statusPagamentoRepository.findById(id)
                .map(statusPagamento -> {
                    modelMapper.map(resource, statusPagamento);
                    return ResponseEntity.ok(statusPagamentoRepository.save(statusPagamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!statusPagamentoRepository.existsById(id)) return ResponseEntity.notFound().build();
        statusPagamentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
