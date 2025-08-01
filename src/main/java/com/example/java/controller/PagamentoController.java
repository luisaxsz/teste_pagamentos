package com.example.java.controller;

import com.example.java.domain.model.Pagamento;
import com.example.java.repository.PagamentoRepository;
import com.example.java.service.PagamentoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagamentos")
@AllArgsConstructor
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PagamentoController {

  private final PagamentoService pagamentoService;
  private final PagamentoRepository pagamentoRepository;
  private final ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<Page<Pagamento>> findAll(
    @RequestParam(defaultValue = "0") int pagina,
    @RequestParam(defaultValue = "10") int tamanho,
    @RequestParam(required = false) String filtro
  ) {
    return ResponseEntity.ok(pagamentoService.listar(pagina, tamanho, filtro));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Pagamento>> findById(@PathVariable Long id) {
    return ResponseEntity.ok(pagamentoRepository.findById(id));
  }

  @GetMapping("/status/{statusPagamento}")
  public ResponseEntity<List<Pagamento>> findByStatusType(@PathVariable String statusPagamento) {
    return ResponseEntity.ok(pagamentoRepository.findAllByStatus_Nome(statusPagamento));
  }

  @PostMapping
  public Pagamento insert(@RequestBody Pagamento resource) {
    return pagamentoService.adicionar(resource);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Pagamento> update(@PathVariable Long id, @RequestBody Pagamento resource) {
    return pagamentoRepository.findById(id)
      .map(statusPagamento -> {
        modelMapper.map(resource, statusPagamento);
        return ResponseEntity.ok(pagamentoRepository.save(statusPagamento));
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/status/{id}")
  public ResponseEntity<Pagamento> updateStatus(@PathVariable Long id, @RequestBody Pagamento resource) {
    return ResponseEntity.ok(pagamentoService.atualizarStatus(id, resource));
  }

  @PutMapping("/inativar/{id}")
  public ResponseEntity<Pagamento> updateStatus(@PathVariable Long id) {
    return ResponseEntity.ok(pagamentoService.inativar(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!pagamentoRepository.existsById(id)) return ResponseEntity.notFound().build();
    pagamentoRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
