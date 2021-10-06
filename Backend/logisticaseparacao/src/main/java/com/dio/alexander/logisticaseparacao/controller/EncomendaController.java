package com.dio.alexander.logisticaseparacao.controller;

import com.dio.alexander.logisticaseparacao.dto.request.EncomendaDTO;
import com.dio.alexander.logisticaseparacao.dto.request.ItemEncomendaDTO;
import com.dio.alexander.logisticaseparacao.dto.response.MessageResponseDTO;
import com.dio.alexander.logisticaseparacao.exception.EncomendaNotFoundException;
import com.dio.alexander.logisticaseparacao.service.EncomendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/encomenda")
@RequiredArgsConstructor
public class EncomendaController {

    private final EncomendaService service;

    @PostMapping("/")
    public ResponseEntity<MessageResponseDTO> create(@RequestBody @Valid EncomendaDTO encomendaDTO) {
        MessageResponseDTO messageResponseDTO = service.createEncomenda(encomendaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(messageResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(messageResponseDTO);
    }

    @GetMapping("/{code}")
    public ResponseEntity<EncomendaDTO> findByCode(@PathVariable String code) throws EncomendaNotFoundException {
        EncomendaDTO encomendaDTOResponse = service.encomendaByCode(code);

        return ResponseEntity.ok(encomendaDTOResponse);
    }

    @PutMapping("/{code}")
    public ResponseEntity<MessageResponseDTO> update(@PathVariable String code,
                                                     @RequestBody @Valid EncomendaDTO encomendaDTO)
            throws EncomendaNotFoundException {

        MessageResponseDTO messageResponseDTO = service.updateEncomenda(code, encomendaDTO);

        return ResponseEntity.accepted().body(messageResponseDTO);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) throws EncomendaNotFoundException {
        service.deleteEncomenda(code);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{encomendaCode}/")
    public ResponseEntity<List<ItemEncomendaDTO>> findAllItens(@PathVariable String encomendaCode)
            throws EncomendaNotFoundException {

        List<ItemEncomendaDTO> itensDTOResponse = service.allItemEncomendas(encomendaCode);

        if(itensDTOResponse.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(itensDTOResponse);
    }
}
