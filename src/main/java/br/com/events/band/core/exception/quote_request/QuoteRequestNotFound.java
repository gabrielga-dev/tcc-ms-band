package br.com.events.band.core.exception.quote_request;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.NotFoundException;

public class QuoteRequestNotFound extends NotFoundException {
    public QuoteRequestNotFound() {
        super(
                "Pedido de orçamento não encontrado!",
                "Não foi possível encontrar o pedido de orçamento requerido!"
        );
    }
}
