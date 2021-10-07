package com.dio.alexander.carrinho.controller;

import com.dio.alexander.carrinho.dto.request.CarrinhoDTO;
import com.dio.alexander.carrinho.dto.request.ItemCarrinhoDTO;
import com.dio.alexander.carrinho.dto.response.MessageResponseDTO;
import com.dio.alexander.carrinho.exception.CarrinhoNotFoundException;
import com.dio.alexander.carrinho.exception.ItemCarrinhoNotFoundException;
import com.dio.alexander.carrinho.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService service;

    @PostMapping("/")
    public ResponseEntity<MessageResponseDTO> create(@RequestBody @Valid CarrinhoDTO carrinhoDTO) {
        MessageResponseDTO messageResponseDTO = service.createCarrinho(carrinhoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(messageResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(messageResponseDTO);
    }

    @GetMapping("/pagar/{code}")
    public ResponseEntity<CarrinhoDTO> pagar(@PathVariable String code) throws CarrinhoNotFoundException {
        return ResponseEntity.ok(service.pagar(code));
    }

    @GetMapping("/{code}")
    public ResponseEntity<CarrinhoDTO> findByCode(@PathVariable String code) throws CarrinhoNotFoundException {
        return ResponseEntity.ok(service.carrinhoByCode(code));
    }

    @PutMapping("/{code}")
    public ResponseEntity<MessageResponseDTO> update(@PathVariable String code,
                                                     @RequestBody @Valid CarrinhoDTO carrinhoDTO)
            throws CarrinhoNotFoundException {

        MessageResponseDTO messageResponseDTO = service.updateCarrinho(code, carrinhoDTO);

        return ResponseEntity.accepted().body(messageResponseDTO);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) throws CarrinhoNotFoundException {
        service.deleteCarrinho(code);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{carrinhoCode}/")
    public ResponseEntity<MessageResponseDTO> addItem(@PathVariable String carrinhoCode,
            @RequestBody @Valid ItemCarrinhoDTO itemCarrinhoDTO) throws CarrinhoNotFoundException {

        MessageResponseDTO messageResponseDTO = service.createItemCarrinho(carrinhoCode, itemCarrinhoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponseDTO);
    }

    @GetMapping("/{carrinhoCode}/")
    public ResponseEntity<List<ItemCarrinhoDTO>> findAllItens(@PathVariable String carrinhoCode)
            throws CarrinhoNotFoundException {

        List<ItemCarrinhoDTO> itensDTOResponse = service.allItemCarrinhos(carrinhoCode);

        if(itensDTOResponse.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(itensDTOResponse);
    }

    @PutMapping("/{carrinhoCode}/{id}")
    public ResponseEntity<MessageResponseDTO> updateItem(@PathVariable String code,
                                                     @PathVariable Long id,
                                                     @RequestBody @Valid ItemCarrinhoDTO itemCarrinhoDTO)
            throws ItemCarrinhoNotFoundException {

        MessageResponseDTO messageResponseDTO = service.updateItemCarrinho(code, id, itemCarrinhoDTO);

        return ResponseEntity.accepted().body(messageResponseDTO);
    }

    @DeleteMapping("/{carrinhoCode}/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String code, @PathVariable Long id)
            throws ItemCarrinhoNotFoundException {

        service.deleteItemCarrinho(code, id);

        return ResponseEntity.noContent().build();
    }
}
