package br.com.events.band.core.exception.quote;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.NotFoundException;

public class QuoteNotFound extends NotFoundException {

    public QuoteNotFound() {
        super(
                "Orçamento não encontrado!",
                "Não foi possível encontrar o orçamento requerido!"
        );
    }
}
