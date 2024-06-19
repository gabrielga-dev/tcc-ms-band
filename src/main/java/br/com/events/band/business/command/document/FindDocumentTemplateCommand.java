package br.com.events.band.business.command.document;

import br.com.events.band.data.model.table.document.DocumentTemplateTable;

public interface FindDocumentTemplateCommand {

    DocumentTemplateTable findByIdOrThrow(Long id);
}
