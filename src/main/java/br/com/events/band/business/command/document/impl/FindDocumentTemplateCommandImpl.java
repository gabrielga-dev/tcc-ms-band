package br.com.events.band.business.command.document.impl;

import br.com.events.band.adapter.repository.DocumentTemplateRepository;
import br.com.events.band.business.command.document.FindDocumentTemplateCommand;
import br.com.events.band.core.exception.document_template.DocumentTemplateNotFoundException;
import br.com.events.band.data.model.table.document.DocumentTemplateTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindDocumentTemplateCommandImpl implements FindDocumentTemplateCommand {

    private final DocumentTemplateRepository documentTemplateRepository;

    @Override
    public DocumentTemplateTable findByIdOrThrow(Long id) {
        return documentTemplateRepository.findById(id).orElseThrow(DocumentTemplateNotFoundException::new);
    }
}
