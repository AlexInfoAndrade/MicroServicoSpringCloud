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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper produtoMapper;

    @Override
    public MessageResponseDTO createProduto(ProdutoDTO produtoDTO) {
        final Produto produtoToSave = produtoMapper.toModel(produtoDTO);

        produtoToSave.setAtivo(true);

        final Produto savedProduto = repository.save(produtoToSave);
        return createMessageResponse(savedProduto.getId(), "Produto salvo com ID::");
    }

    @Override
    public ProdutoDTO produtoById(Long id) throws ProdutoNotFoundException {
        return produtoMapper.toDTO(
                verifyIfExists(id)
        );
    }

    @Override
    public List<ProdutoDTO> allProdutos() {
        return repository.findAll().stream().map(
                (produto) -> produtoMapper.toDTO(produto)
        ).collect(Collectors.toList());
    }

    @Override
    public MessageResponseDTO updateProduto(Long id, ProdutoDTO produtoDTO)
            throws ProdutoNotFoundException {

        final Produto produtoExistente = verifyIfExists(id);
        final Produto produtoToUpdate = produtoMapper.toModel(produtoDTO);

        produtoToUpdate.setId(id);
        produtoToUpdate.setDataInclusao(produtoExistente.getDataInclusao());

        final Produto updatedProduto = repository.save(produtoToUpdate);

        return createMessageResponse(updatedProduto.getId(), "Produto alterado com ID::");
    }

    @Override
    public void deleteProduto(Long id) throws ProdutoNotFoundException {
        verifyIfExists(id);

        repository.deleteById(id);
    }

    private Produto verifyIfExists(Long id) throws ProdutoNotFoundException {
        return repository.findById(id)
            .orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .id(id)
                .message(message + id)
                .build();
    }
}
