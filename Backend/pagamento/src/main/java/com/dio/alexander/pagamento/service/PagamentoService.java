package com.dio.alexander.pagamento.service;

import com.dio.alexander.pagamento.dto.request.PagamentoDTO;
import com.dio.alexander.pagamento.dto.response.MessageResponseDTO;
import com.dio.alexander.pagamento.exception.PagamentoNotFoundException;

import java.util.List;

public interface PagamentoService {

    MessageResponseDTO createPagamento(PagamentoDTO pagamentoDTO);

    List<PagamentoDTO> listarPagamentos();

    PagamentoDTO pagamentoByCode(String code) throws PagamentoNotFoundException;

    MessageResponseDTO updatePagamento(String code, PagamentoDTO pagamentoDTO)
            throws PagamentoNotFoundException;

    void deletePagamento(String code) throws PagamentoNotFoundException;
}
