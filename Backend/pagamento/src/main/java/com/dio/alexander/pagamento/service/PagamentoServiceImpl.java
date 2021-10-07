package com.dio.alexander.pagamento.service;

import com.dio.alexander.pagamento.dto.request.PagamentoDTO;
import com.dio.alexander.pagamento.dto.response.MessageResponseDTO;
import com.dio.alexander.pagamento.exception.PagamentoNotFoundException;
import com.dio.alexander.pagamento.mapper.PagamentoMapper;
import com.dio.alexander.pagamento.model.Pagamento;
import com.dio.alexander.pagamento.repository.PagamentoRepository;
import com.dio.alexander.pagamento.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final UUIDUtil uuidUtil;

    //    curl -H 'Content-Type: application/json' -X POST -d '{"valor":30.0}' http://localhost:8085/v1/pagamento/

    @Override
    public MessageResponseDTO createPagamento(PagamentoDTO pagamentoDTO) {
        final Pagamento encomendaToSave = pagamentoMapper.toModel(pagamentoDTO);

        encomendaToSave.setCode(uuidUtil.createUUID().toString());
        encomendaToSave.setStatus(Pagamento.Status.PENDENTE);

        final Pagamento savedPagamento = pagamentoRepository.save(encomendaToSave);

        return createMessageResponse(
                savedPagamento.getId(),
                savedPagamento.getCode(),
                "Pagamento criado com ID::"
        );
    }

    @Override
    public List<PagamentoDTO> listarPagamentos() {
        return pagamentoRepository.findAll().stream()
                .map(pagamento -> pagamentoMapper.toDTO(pagamento))
                .collect(Collectors.toList());
    }

    @Override
    public PagamentoDTO pagamentoByCode(String code) throws PagamentoNotFoundException {
        return pagamentoMapper.toDTO(
                verifyIfExists(code)
        );
    }

    @Override
    public MessageResponseDTO updatePagamento(String code, PagamentoDTO pagamentoDTO) throws PagamentoNotFoundException {
        final Pagamento pagamentoExistente = verifyIfExists(code);
        final Pagamento pagamentoToUpdate = pagamentoMapper.toModel(pagamentoDTO);

        pagamentoToUpdate.setId(pagamentoExistente.getId());
        pagamentoToUpdate.setCode(pagamentoExistente.getCode());
        pagamentoToUpdate.setDataInclusao(pagamentoExistente.getDataInclusao());

        final Pagamento updatedCarrinho = pagamentoRepository.save(pagamentoToUpdate);

        return createMessageResponse(updatedCarrinho.getId(), code, "Pagamento alterado com ID::");
    }

    @Override
    public void deletePagamento(String code) throws PagamentoNotFoundException {
        pagamentoRepository.delete(verifyIfExists(code));
    }

    private Pagamento verifyIfExists(String code) throws PagamentoNotFoundException {
        return pagamentoRepository.findByCode(code)
                .orElseThrow(() -> new PagamentoNotFoundException());
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
