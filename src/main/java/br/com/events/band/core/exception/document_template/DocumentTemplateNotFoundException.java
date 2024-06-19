package br.com.events.band.core.exception.document_template;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.NotFoundException;

public class DocumentTemplateNotFoundException extends NotFoundException {
    public DocumentTemplateNotFoundException() {
        super(
                "Padrão de documento não encontrado!",
                "Não foi possível encontrar o padrão de documento requerido!"
        );
    }
}
