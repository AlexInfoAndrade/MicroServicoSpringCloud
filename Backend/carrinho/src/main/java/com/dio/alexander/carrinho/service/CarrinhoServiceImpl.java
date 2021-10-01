package com.dio.alexander.carrinho.service;

import com.dio.alexander.carrinho.Repository.CarrinhoRepository;
import com.dio.alexander.carrinho.Repository.ItemCarrinhoRepository;
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
import com.dio.alexander.carrinho.streaming.CarrinhoCreated;
import com.dio.alexander.carrinho.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrinhoServiceImpl implements CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final CarrinhoMapper carrinhoMapper = CarrinhoMapper.INSTANCE;
    private final ItemCarrinhoMapper itemCarrinhoMapper = ItemCarrinhoMapper.INSTANCE;
    private final UUIDUtil uuidUtil;
    private final CarrinhoCreated carrinhoCreated;

    @Override
    public MessageResponseDTO createCarrinho(CarrinhoDTO carrinhoDTO) {
        final Carrinho carrinhoToSave = carrinhoMapper.toModel(carrinhoDTO);

        carrinhoToSave.setCode(uuidUtil.createUUID().toString());
        carrinhoToSave.setStatus(Carrinho.Status.CRIADO);
        carrinhoToSave.setItens(
                carrinhoDTO.getItens().stream()
                        .map(item -> itemCarrinhoMapper.toModel(item))
                        .collect(Collectors.toList())
        );

        final Carrinho savedCarrinho = carrinhoRepository.save(carrinhoToSave);

        return createMessageResponse(savedCarrinho.getId(), "Carrinho salvo com ID::");
    }

    @Override
    public void pagar(String code) throws CarrinhoNotFoundException {
        final Carrinho carrinho = verifyIfExists(code);

        final CarrinhoResponse carrinhoResponse = CarrinhoResponse.builder()
                .code(carrinho.getCode())
                .status(carrinho.getStatus().name())
                .build();

        carrinhoCreated.output().send(MessageBuilder.withPayload(carrinhoResponse).build());
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

        return createMessageResponse(updatedCarrinho.getId(), "Carrinho alterado com ID::");
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

        return createMessageResponse(savedItem.getId(), "Item salvo com ID::");
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

        return createMessageResponse(updatedItem.getId(), "Item alterado com ID::");
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

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .id(id)
                .message(message + id)
                .build();
    }
}