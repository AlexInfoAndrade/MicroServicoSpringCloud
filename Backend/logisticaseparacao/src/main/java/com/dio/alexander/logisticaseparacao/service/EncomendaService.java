package com.dio.alexander.logisticaseparacao.service;

import com.dio.alexander.logisticaseparacao.dto.request.EncomendaDTO;
import com.dio.alexander.logisticaseparacao.dto.request.ItemEncomendaDTO;
import com.dio.alexander.logisticaseparacao.dto.response.MessageResponseDTO;
import com.dio.alexander.logisticaseparacao.exception.EncomendaNotFoundException;

import java.util.List;

public interface EncomendaService {

    MessageResponseDTO createEncomenda(EncomendaDTO encomendaDTO);

    EncomendaDTO encomendaByCode(String code) throws EncomendaNotFoundException;

    MessageResponseDTO updateEncomenda(String code, EncomendaDTO encomendaDTO)
            throws EncomendaNotFoundException;

    void deleteEncomenda(String code) throws EncomendaNotFoundException;

    List<ItemEncomendaDTO> allItemEncomendas(String encomendaCode) throws EncomendaNotFoundException;
}
