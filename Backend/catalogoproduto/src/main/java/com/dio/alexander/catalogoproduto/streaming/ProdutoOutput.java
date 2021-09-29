package com.dio.alexander.catalogoproduto.streaming;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProdutoOutput {

    String OUTPUT = "produto-output";

    @Output(OUTPUT)
    MessageChannel output();
}
