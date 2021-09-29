package com.dio.alexander.catalogoproduto.service;

import com.dio.alexander.catalogoproduto.dto.request.ProdutoDTO;
import com.dio.alexander.catalogoproduto.dto.response.MessageResponseDTO;
import com.dio.alexander.catalogoproduto.exception.ProdutoNotFoundException;
import com.dio.alexander.catalogoproduto.mapper.ProdutoMapper;
import com.dio.alexander.catalogoproduto.model.Produto;
import com.dio.alexander.catalogoproduto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper produtoMapper = ProdutoMapper.INSTANCE;

    @Override
    public MessageResponseDTO createProduto(ProdutoDTO produtoDTO) {
        Produto produtoToSave = produtoMapper.toModel(produtoDTO);

        Produto savedProduto = repository.save(produtoToSave);
        return createMessageResponse(savedProduto.getId(), "Produto salvo com ID::");
    }

    @Override
    public Optional<ProdutoDTO> produtoById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ProdutoDTO> allProdutos() {
        return null;
    }

    @Override
    public MessageResponseDTO updateProduto(ProdutoDTO produtoDTO) {
        return null;
    }

    @Override
    public void deleteProduto(Long id) {
        repository.deleteById(id);
    }

    private ProdutoDTO verifyIfExists(Long id) throws ProdutoNotFoundException {
        return produtoMapper.toDTO(
            repository.findById(id)
            .orElseThrow(() -> new ProdutoNotFoundException(id))
        );
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
