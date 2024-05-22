package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.document.DocumentTemplateTable;

import java.util.Optional;

public interface DocumentTemplateRepository {

    Optional<DocumentTemplateTable> findById(Long id);
}
