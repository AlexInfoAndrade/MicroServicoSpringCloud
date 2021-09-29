package com.dio.alexander.catalogoproduto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends Exception {

    public ProdutoNotFoundException(Long id) {
        super("Produto não encontrado com ID::" + id);
    }
}
