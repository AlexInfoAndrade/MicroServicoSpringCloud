package com.dio.alexander.pagamento.controller;

import com.dio.alexander.pagamento.dto.request.PagamentoDTO;
import com.dio.alexander.pagamento.dto.response.MessageResponseDTO;
import com.dio.alexander.pagamento.exception.PagamentoNotFoundException;
import com.dio.alexander.pagamento.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/pagamento")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService service;

    @PostMapping("/")
    public ResponseEntity<MessageResponseDTO> create(@RequestBody @Valid PagamentoDTO pagamentoDTO) {
        MessageResponseDTO messageResponseDTO = service.createPagamento(pagamentoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(messageResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(messageResponseDTO);
    }

    @GetMapping("/{code}")
    public ResponseEntity<PagamentoDTO> findByCode(@PathVariable String code) throws PagamentoNotFoundException {
        PagamentoDTO pagamentoDTOResponse = service.pagamentoByCode(code);

        return ResponseEntity.ok(pagamentoDTOResponse);
    }

    @PutMapping("/{code}")
    public ResponseEntity<MessageResponseDTO> update(@PathVariable String code,
                                                     @RequestBody @Valid PagamentoDTO pagamentoDTO)
            throws PagamentoNotFoundException {

        MessageResponseDTO messageResponseDTO = service.updatePagamento(code, pagamentoDTO);

        return ResponseEntity.accepted().body(messageResponseDTO);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) throws PagamentoNotFoundException {
        service.deletePagamento(code);

        return ResponseEntity.noContent().build();
    }
}
