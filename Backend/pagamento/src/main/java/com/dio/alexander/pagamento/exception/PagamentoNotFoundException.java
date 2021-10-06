package com.dio.alexander.pagamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PagamentoNotFoundException extends Exception {

    public PagamentoNotFoundException() {
        super("Pagamento não encontrado");
    }
}
