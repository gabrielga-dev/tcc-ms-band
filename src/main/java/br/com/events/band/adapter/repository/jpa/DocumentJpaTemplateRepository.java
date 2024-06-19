package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.DocumentTemplateRepository;
import br.com.events.band.data.model.table.document.DocumentTemplateTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentJpaTemplateRepository extends DocumentTemplateRepository, JpaRepository<DocumentTemplateTable, Long> {
}
