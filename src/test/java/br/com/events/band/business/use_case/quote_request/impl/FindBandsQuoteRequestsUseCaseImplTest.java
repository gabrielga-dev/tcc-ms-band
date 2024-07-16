package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.quote_request.FindBandsQuoteRequestsCommand;
import br.com.events.band.core.exception.band.BandNotFoundException;
import br.com.events.band.data.io.quote_request.criteria.FindQuoteRequestCriteria;
import br.com.events.band.data.model.table.band.BandTableMock;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindBandsQuoteRequestsUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindBandsQuoteRequestsUseCaseImplTest {

    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private FindBandsQuoteRequestsCommand findBandsQuoteRequestsCommand;
    @InjectMocks
    private FindBandsQuoteRequestsUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandNotFoundException")
    void executeWhenBandIsNotFoundThenThrowsBandNotFoundException() {
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.empty());

        var criteria = new FindQuoteRequestCriteria();
        Assertions.assertThrows(
                BandNotFoundException.class,
                () -> useCase.execute(MockConstants.STRING, criteria, mock(Pageable.class))
        );

        verify(findBandCommand, atMostOnce()).byUuid(anyString());
        verify(findBandsQuoteRequestsCommand, never())
                .execute(anyString(), any(FindQuoteRequestCriteria.class), any(Pageable.class));
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNotFoundException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        var band = BandTableMock.build();
        band.setActive(false);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(band));

        var criteria = new FindQuoteRequestCriteria();
        Assertions.assertThrows(
                BandNotFoundException.class,
                () -> useCase.execute(MockConstants.STRING, criteria, mock(Pageable.class))
        );

        verify(findBandCommand, atMostOnce()).byUuid(anyString());
        verify(findBandsQuoteRequestsCommand, never())
                .execute(anyString(), any(FindQuoteRequestCriteria.class), any(Pageable.class));
    }

    @Test
    @DisplayName("execute - when band is active, then returns quote requests")
    void executeWhenBandIsActiveThenReturnQuoteRequests() {
        var band = BandTableMock.build();
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(band));
        when(
                findBandsQuoteRequestsCommand.execute(
                        anyString(),
                        any(FindQuoteRequestCriteria.class),
                        any(Pageable.class)
                )
        ).thenReturn(new PageImpl<>(List.of(QuoteRequestTableMock.build())));

        var criteria = new FindQuoteRequestCriteria();
        var response = useCase.execute(MockConstants.STRING, criteria, mock(Pageable.class));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getContent());
        Assertions.assertFalse(response.getContent().isEmpty());
        Assertions.assertEquals(1, response.getContent().size());

        verify(findBandCommand, atMostOnce()).byUuid(anyString());
        verify(findBandsQuoteRequestsCommand, atMostOnce())
                .execute(anyString(), any(FindQuoteRequestCriteria.class), any(Pageable.class));
    }
}
