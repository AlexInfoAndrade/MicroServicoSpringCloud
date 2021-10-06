package com.dio.alexander.logisticaseparacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EncomendaNotFoundException extends Exception {

    public EncomendaNotFoundException() {
        super("Encomenda não encontrado");
    }
}
