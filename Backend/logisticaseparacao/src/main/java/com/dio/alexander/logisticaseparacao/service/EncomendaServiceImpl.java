package com.dio.alexander.logisticaseparacao.service;

import com.dio.alexander.logisticaseparacao.dto.request.EncomendaDTO;
import com.dio.alexander.logisticaseparacao.dto.request.ItemEncomendaDTO;
import com.dio.alexander.logisticaseparacao.dto.response.MessageResponseDTO;
import com.dio.alexander.logisticaseparacao.exception.EncomendaNotFoundException;
import com.dio.alexander.logisticaseparacao.mapper.EncomendaMapper;
import com.dio.alexander.logisticaseparacao.mapper.ItemEncomendaMapper;
import com.dio.alexander.logisticaseparacao.model.Encomenda;
import com.dio.alexander.logisticaseparacao.repository.EncomendaRepository;
import com.dio.alexander.logisticaseparacao.repository.ItemEncomendaRepository;
import com.dio.alexander.logisticaseparacao.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EncomendaServiceImpl implements EncomendaService {

    private final EncomendaRepository encomendaRepository;
    private final ItemEncomendaRepository itemEncomendaRepository;
    private final EncomendaMapper encomendaMapper;
    private final ItemEncomendaMapper itemEncomendaMapper;
    private final UUIDUtil uuidUtil;

//    curl -H 'Content-Type: application/json' -X POST -d '{"itens":[{"prodtudoId":563, "quantidade":2.0}]}' http://localhost:8084/v1/encomenda/

    @Override
    public MessageResponseDTO createEncomenda(EncomendaDTO encomendaDTO) {
//        final List<ItemEncomendaDTO> itensDTO = encomendaDTO.getItens();
        final Encomenda encomendaToSave = encomendaMapper.toModel(encomendaDTO);

        encomendaToSave.setCode(uuidUtil.createUUID().toString());
        encomendaToSave.setStatus(Encomenda.Status.CRIADO);
//        encomendaToSave.setItens(
//                itensDTO.stream()
//                        .map(item -> itemEncomendaMapper.toModel(item))
//                        .collect(Collectors.toList())
//        );

        final Encomenda savedEncomenda = encomendaRepository.save(encomendaToSave);

        return createMessageResponse(
                savedEncomenda.getId(),
                savedEncomenda.getCode(),
                "Encomenda salva com ID::"
        );
    }

    @Override
    public EncomendaDTO encomendaByCode(String code) throws EncomendaNotFoundException {
        return encomendaMapper.toDTO(
                verifyIfExists(code)
        );
    }

    @Override
    public MessageResponseDTO updateEncomenda(String code, EncomendaDTO encomendaDTO) throws EncomendaNotFoundException {
        final Encomenda encomendaExistente = verifyIfExists(code);
        final Encomenda encomendaToUpdate = encomendaMapper.toModel(encomendaDTO);

        encomendaToUpdate.setId(encomendaExistente.getId());
        encomendaToUpdate.setCode(encomendaExistente.getCode());
        encomendaToUpdate.setDataInclusao(encomendaExistente.getDataInclusao());

        final Encomenda updatedEncomenda = encomendaRepository.save(encomendaToUpdate);

        return createMessageResponse(updatedEncomenda.getId(), code, "Encomenda alterada com ID::");
    }

    @Override
    public void deleteEncomenda(String code) throws EncomendaNotFoundException {
        encomendaRepository.delete(verifyIfExists(code));
    }

    @Override
    public List<ItemEncomendaDTO> allItemEncomendas(String encomendaCode) throws EncomendaNotFoundException {
        final Encomenda carrinho = verifyIfExists(encomendaCode);

        return carrinho.getItens().stream()
                .map(item -> itemEncomendaMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    private Encomenda verifyIfExists(String code) throws EncomendaNotFoundException {
        return encomendaRepository.findByCode(code)
                .orElseThrow(() -> new EncomendaNotFoundException());
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
