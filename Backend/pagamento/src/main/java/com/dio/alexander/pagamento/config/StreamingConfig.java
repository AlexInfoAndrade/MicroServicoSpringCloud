package com.dio.alexander.pagamento.config;

import com.dio.alexander.pagamento.streaming.CarrinhoProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(CarrinhoProcessor.class)
public class StreamingConfig {
}
