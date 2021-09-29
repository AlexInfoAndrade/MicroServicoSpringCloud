package com.dio.alexander.catalogoproduto.streaming;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProdutoInput {

    String INPUT = "produto-input";

    @Input(INPUT)
    SubscribableChannel input();
}
