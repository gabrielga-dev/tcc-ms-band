package br.com.events.band.business.command.quote_request;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.QuoteRequestRepository;
import br.com.events.band.data.io.quote_request.criteria.FindQuoteRequestCriteriaMock;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindBandsQuoteRequestsCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindBandsQuoteRequestsCommandTest {

    @InjectMocks
    private FindBandsQuoteRequestsCommand command;

    @Mock
    private QuoteRequestRepository quoteRequestRepository;

    @Test
    @DisplayName("execute - when called return found result")
    void executeWhenCalledReturnFoundResult() {
        var quoteRequest = QuoteRequestTableMock.build();
        when(
                quoteRequestRepository.findByBandUuid(
                        anyString(),
                        anyList(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        any(Pageable.class)
                )
        ).thenReturn(new PageImpl<>(List.of(quoteRequest)));

        var criteria = FindQuoteRequestCriteriaMock.build();
        var result = command.execute(MockConstants.STRING, criteria, mock(Pageable.class));

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getContent());
        Assertions.assertFalse(result.getContent().isEmpty());
        Assertions.assertNotNull(result.getContent().get(0));
        Assertions.assertEquals(quoteRequest, result.getContent().get(0));

        verify(quoteRequestRepository, times(1)).findByBandUuid(
                anyString(),
                anyList(),
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                any(Pageable.class)
        );
    }
}
