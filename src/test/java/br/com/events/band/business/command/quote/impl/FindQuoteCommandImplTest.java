package br.com.events.band.business.command.quote.impl;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.QuoteRepository;
import br.com.events.band.core.exception.quote.QuoteNotFound;
import br.com.events.band.data.model.table.musician.MusicianTypeTableMock;
import br.com.events.band.data.model.table.quote.QuoteTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindQuoteCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindQuoteCommandImplTest {

    @InjectMocks
    private FindQuoteCommandImpl command;

    @Mock
    private QuoteRepository quoteRepository;

    @Test
    @DisplayName("byUuidOrThrow - when quote is found then return found result")
    void byUuidOrThrowWhenQuoteIsFoundThenReturnFoundResult() {
        var quoteTable = QuoteTableMock.build();
        when(quoteRepository.findById(anyString())).thenReturn(Optional.of(quoteTable));

        var returned = command.byUuidOrThrow(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(quoteTable, returned);

        verify(quoteRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("byUuidOrThrow - when quote is not found then throws QuoteNotFound")
    void byUuidOrThrowWhenQuoteIsNotFoundThenThrowsQuoteNotFound() {
        when(quoteRepository.findById(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                QuoteNotFound.class,
                () -> command.byUuidOrThrow(MockConstants.STRING)
        );

        verify(quoteRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("bySpecification - when called return found result")
    void bySpecificationWhenCalledReturnFoundResult() {
        var musicianType = MusicianTypeTableMock.build();
        when(quoteRepository.findAll(any(Specification.class))).thenReturn(List.of(musicianType));

        var returned = command.bySpecification(mock(Specification.class));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertNotNull(returned.get(0));
        Assertions.assertEquals(musicianType, returned.get(0));

        verify(quoteRepository, times(1)).findAll(any(Specification.class));
    }
}
