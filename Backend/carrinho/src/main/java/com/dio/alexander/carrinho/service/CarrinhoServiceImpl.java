package com.dio.alexander.carrinho.service;

import com.dio.alexander.carrinho.dto.request.CarrinhoDTO;
import com.dio.alexander.carrinho.dto.request.ItemCarrinhoDTO;
import com.dio.alexander.carrinho.dto.response.CarrinhoResponse;
import com.dio.alexander.carrinho.dto.response.MessageResponseDTO;
import com.dio.alexander.carrinho.exception.CarrinhoNotFoundException;
import com.dio.alexander.carrinho.exception.ItemCarrinhoNotFoundException;
import com.dio.alexander.carrinho.mapper.CarrinhoMapper;
import com.dio.alexander.carrinho.mapper.ItemCarrinhoMapper;
import com.dio.alexander.carrinho.model.Carrinho;
import com.dio.alexander.carrinho.model.ItemCarrinho;
import com.dio.alexander.carrinho.repository.CarrinhoRepository;
import com.dio.alexander.carrinho.repository.ItemCarrinhoRepository;
import com.dio.alexander.carrinho.streaming.CarrinhoCreated;
import com.dio.alexander.carrinho.streaming.PagamentoReservado;
import com.dio.alexander.carrinho.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrinhoServiceImpl implements CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final CarrinhoMapper carrinhoMapper;
    private final ItemCarrinhoMapper itemCarrinhoMapper;
    private final UUIDUtil uuidUtil;
    private final CarrinhoCreated carrinhoCreated;

//    curl -H 'Content-Type: application/json' -X POST http://localhost:8082/v1/carrinho/ -d '{"itens":[{"prodtudoId":563, "valor":15.0, "quantidade":2.0}]}'

    @Override
    public MessageResponseDTO createCarrinho(CarrinhoDTO carrinhoDTO) {
        final List<ItemCarrinhoDTO> itensDTO = carrinhoDTO.getItens();
        final Carrinho carrinhoToSave = carrinhoMapper.toModel(carrinhoDTO);

        carrinhoToSave.setCode(uuidUtil.createUUID().toString());
        carrinhoToSave.setStatus(Carrinho.Status.CRIADO);
//        carrinhoToSave.setItens(
//                itensDTO.stream()
//                        .map(item -> itemCarrinhoMapper.toModel(item))
//                        .collect(Collectors.toList())
//        );

        final Carrinho savedCarrinho = carrinhoRepository.save(carrinhoToSave);

        return createMessageResponse(
                savedCarrinho.getId(),
                savedCarrinho.getCode(),
                "Carrinho salvo com ID::"
        );
    }

    @Override
    public CarrinhoDTO pagar(String code) throws CarrinhoNotFoundException {
        Carrinho carrinho = verifyIfExists(code);

        final CarrinhoResponse carrinhoResponse = CarrinhoResponse.builder()
                .code(carrinho.getCode())
                .status(carrinho.getStatus().name())
                .valor(valorItens(carrinho.getItens()))
                .build();

        carrinhoCreated.output().send(MessageBuilder.withPayload(carrinhoResponse).build());

        return carrinhoMapper.toDTO(carrinho);
    }

    private Double valorItens(List<ItemCarrinho> itens) {
        Double total = 0.0;

        for(ItemCarrinho item : itens) {
            total += item.getValor();
        }

        return total;
    }

    @Override
    public CarrinhoDTO carrinhoByCode(String code) throws CarrinhoNotFoundException {
        return carrinhoMapper.toDTO(
                verifyIfExists(code)
        );
    }

    @Override
    public MessageResponseDTO updateCarrinho(String code, CarrinhoDTO carrinhoDTO) throws CarrinhoNotFoundException {
        final Carrinho carrinhoExistente = verifyIfExists(code);
        final Carrinho carrinhoToUpdate = carrinhoMapper.toModel(carrinhoDTO);

        carrinhoToUpdate.setId(carrinhoExistente.getId());
        carrinhoToUpdate.setCode(carrinhoExistente.getCode());
        carrinhoToUpdate.setDataInclusao(carrinhoExistente.getDataInclusao());

        final Carrinho updatedCarrinho = carrinhoRepository.save(carrinhoToUpdate);

        return createMessageResponse(updatedCarrinho.getId(), code, "Carrinho alterado com ID::");
    }

    @Override
    public void deleteCarrinho(String code) throws CarrinhoNotFoundException {
        carrinhoRepository.delete(verifyIfExists(code));
    }

    @Override
    public MessageResponseDTO createItemCarrinho(String carrinhoCode, ItemCarrinhoDTO itemCarrinhoDTO)
            throws CarrinhoNotFoundException {

        final Carrinho carrinho = verifyIfExists(carrinhoCode);

        final ItemCarrinho itemToSave = itemCarrinhoMapper.toModel(itemCarrinhoDTO);

        itemToSave.setId(itemCarrinhoDTO.getId());
        itemToSave.setCarrinho(carrinho);

        final ItemCarrinho savedItem = itemCarrinhoRepository.save(itemToSave);

        return createMessageResponse(savedItem.getId(), carrinhoCode, "Item salvo com ID::");
    }

    @Override
    public List<ItemCarrinhoDTO> allItemCarrinhos(String carrinhoCode) throws CarrinhoNotFoundException {
        final Carrinho carrinho = verifyIfExists(carrinhoCode);

        return carrinho.getItens().stream()
                .map(item -> itemCarrinhoMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponseDTO updateItemCarrinho(String carrinhoCode, Long id, ItemCarrinhoDTO itemCarrinhoDTO)
            throws ItemCarrinhoNotFoundException {

        final ItemCarrinho itemExistente = verifyItemIfExists(carrinhoCode, id);
        final ItemCarrinho itemToUpdate = itemCarrinhoMapper.toModel(itemCarrinhoDTO);

        itemToUpdate.setId(itemExistente.getId());
        itemToUpdate.setCarrinho(itemExistente.getCarrinho());

        final ItemCarrinho updatedItem = itemCarrinhoRepository.save(itemToUpdate);

        return createMessageResponse(updatedItem.getId(), carrinhoCode, "Item alterado com ID::");
    }

    @Override
    public void deleteItemCarrinho(String carrinhoCode, Long id) throws ItemCarrinhoNotFoundException {
        verifyItemIfExists(carrinhoCode, id);

        itemCarrinhoRepository.deleteById(id);
    }

    private Carrinho verifyIfExists(String code) throws CarrinhoNotFoundException {
        return carrinhoRepository.findByCode(code)
                .orElseThrow(() -> new CarrinhoNotFoundException());
    }

    private ItemCarrinho verifyItemIfExists(String carrinhoCode, Long id) throws ItemCarrinhoNotFoundException {
        return itemCarrinhoRepository.findByCarrinhoCodeAndId(carrinhoCode, id)
                .orElseThrow(() -> new ItemCarrinhoNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String code, String message) {
        return MessageResponseDTO
                .builder()
                .id(id)
                .code(code)
                .message(message + id)
                .build();
    }
}
