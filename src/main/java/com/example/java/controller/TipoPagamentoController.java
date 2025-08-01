package com.example.java.controller;

import com.example.java.domain.model.TipoPagamento;
import com.example.java.repository.TipoPagamentoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipo-pagamento")
@AllArgsConstructor
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class TipoPagamentoController {

    private final TipoPagamentoRepository tipoPagamentoRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<TipoPagamento>> findAll() {
        return ResponseEntity.ok(tipoPagamentoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TipoPagamento>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoPagamentoRepository.findById(id));
    }

    @PostMapping
    public TipoPagamento insert(@RequestBody TipoPagamento resource) {
        return tipoPagamentoRepository.save(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPagamento> update(@PathVariable Long id, @RequestBody TipoPagamento resource) {
        return tipoPagamentoRepository.findById(id)
                .map(tipoPagamento -> {
                    modelMapper.map(resource, tipoPagamento);
                    return ResponseEntity.ok(tipoPagamentoRepository.save(tipoPagamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!tipoPagamentoRepository.existsById(id)) return ResponseEntity.notFound().build();
        tipoPagamentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
