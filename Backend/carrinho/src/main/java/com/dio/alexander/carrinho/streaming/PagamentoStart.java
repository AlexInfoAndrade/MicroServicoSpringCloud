package com.dio.alexander.carrinho.streaming;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PagamentoStart {

    String INPUT = "pagamento-start-input";

    @Input(INPUT)
    SubscribableChannel input();
}
