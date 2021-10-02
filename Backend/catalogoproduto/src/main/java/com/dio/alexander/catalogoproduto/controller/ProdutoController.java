package com.dio.alexander.catalogoproduto.controller;

import com.dio.alexander.catalogoproduto.dto.request.ProdutoDTO;
import com.dio.alexander.catalogoproduto.dto.response.MessageResponseDTO;
import com.dio.alexander.catalogoproduto.exception.ProdutoNotFoundException;
import com.dio.alexander.catalogoproduto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping("/")
    public ResponseEntity<MessageResponseDTO> create(@RequestBody @Valid ProdutoDTO produtoDTO) {

        MessageResponseDTO messageResponseDTO = service.createProduto(produtoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(messageResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(messageResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) throws ProdutoNotFoundException {
        ProdutoDTO produtoDTOResponse = service.produtoById(id);

        return ResponseEntity.ok(produtoDTOResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<ProdutoDTO> listProdutoDTOResponse = service.allProdutos();

        if(listProdutoDTOResponse.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listProdutoDTOResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody @Valid ProdutoDTO produtoDTO)
            throws ProdutoNotFoundException {

        MessageResponseDTO messageResponseDTO = service.updateProduto(id, produtoDTO);

        return ResponseEntity.accepted().body(messageResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ProdutoNotFoundException {
        service.deleteProduto(id);

        return ResponseEntity.noContent().build();
    }
}
