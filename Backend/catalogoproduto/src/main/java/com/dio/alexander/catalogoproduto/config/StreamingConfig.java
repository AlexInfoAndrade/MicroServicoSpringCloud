package com.dio.alexander.catalogoproduto.config;

import com.dio.alexander.catalogoproduto.streaming.ProdutoInput;
import com.dio.alexander.catalogoproduto.streaming.ProdutoOutput;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        ProdutoOutput.class,
        ProdutoInput.class
})
public class StreamingConfig {
}
