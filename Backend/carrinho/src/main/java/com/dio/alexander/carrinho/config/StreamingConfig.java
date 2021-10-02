package com.dio.alexander.carrinho.config;

import com.dio.alexander.carrinho.streaming.CarrinhoCreated;
import com.dio.alexander.carrinho.streaming.PagamentoReservado;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        CarrinhoCreated.class,
        PagamentoReservado.class
})
public class StreamingConfig {
}
