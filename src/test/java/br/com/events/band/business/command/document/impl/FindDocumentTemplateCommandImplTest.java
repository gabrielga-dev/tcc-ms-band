package br.com.events.band.business.command.document.impl;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.DocumentTemplateRepository;
import br.com.events.band.core.exception.document_template.DocumentTemplateNotFoundException;
import br.com.events.band.data.model.table.document.DocumentTemplateTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindDocumentTemplateCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindDocumentTemplateCommandImplTest {

    @InjectMocks
    private FindDocumentTemplateCommandImpl command;

    @Mock
    private DocumentTemplateRepository documentTemplateRepository;

    @Test
    @DisplayName("findByIdOrThrow - when document template is not found then throw DocumentTemplateNotFoundException")
    void findByIdOrThrowWhenDocumentTemplateIsNotFoundThenThrowDocumentTemplateNotFoundException() {
        when(documentTemplateRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                DocumentTemplateNotFoundException.class,
                () -> command.findByIdOrThrow(MockConstants.LONG)
        );

        verify(documentTemplateRepository, times(1)).findById(MockConstants.LONG);
    }

    @Test
    @DisplayName("findByIdOrThrow - when document template is found then return found document template")
    void findByIdOrThrowWhenDocumentTemplateIsFoundThenReturnFoundDocumentTemplate() {
        var documentTemplate = DocumentTemplateTableMock.build();
        when(documentTemplateRepository.findById(anyLong())).thenReturn(Optional.of(documentTemplate));

        var returned = command.findByIdOrThrow(MockConstants.LONG);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(documentTemplate, returned);

        verify(documentTemplateRepository, times(1)).findById(MockConstants.LONG);
    }
}
