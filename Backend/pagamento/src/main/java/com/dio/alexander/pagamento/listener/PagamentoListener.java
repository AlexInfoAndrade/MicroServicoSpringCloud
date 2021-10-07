package com.dio.alexander.pagamento.listener;

import com.dio.alexander.carrinho.event.CarrinhoCreated;
import com.dio.alexander.pagamento.dto.request.PagamentoDTO;
import com.dio.alexander.pagamento.dto.response.MessageResponseDTO;
import com.dio.alexander.pagamento.service.PagamentoService;
import com.dio.alexander.pagamento.streaming.CarrinhoProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagamentoListener {

    private final PagamentoService service;

    @StreamListener(CarrinhoProcessor.INPUT)
    public void handler(CarrinhoCreated carrinhoCreated) {
        PagamentoDTO pagamentoDTO = PagamentoDTO.builder()
                .carrinhoCode(carrinhoCreated.getCode().toString())
                .status(carrinhoCreated.getStatus().toString())
                .valor((Double) carrinhoCreated.getValor())
                .build();

        MessageResponseDTO msg = service.createPagamento(pagamentoDTO);
        System.out.println("Code pagamento::" + msg.getCode());
    }
}
