package com.dio.alexander.carrinho.streaming;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

public interface CarrinhoCreated {

    String OUTPUT = "carrinho-created-output";

    @Output(OUTPUT)
    MessageChannel output();
}
