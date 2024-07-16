package br.com.events.band.business.use_case.quote.impl;

import br.com.events.band.business.command.quote.FindQuoteCommand;
import br.com.events.band.data.io.quote.request.DashboardRequest;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link GenerateDashboardUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class GenerateDashboardUseCaseImplTest {

    @Mock
    private FindQuoteCommand findQuoteCommand;
    @InjectMocks
    private GenerateDashboardUseCaseImpl useCase;

    @Test
    @DisplayName("execute -when called, then return dashboard")
    void executeWhenCalledReturnDashboard() {
        var quote1 = QuoteTableMock.build();
        var quote2 = QuoteTableMock.build();

        when(findQuoteCommand.bySpecification(any(Specification.class))).thenReturn(List.of(quote1, quote2));

        var result = useCase.execute(new DashboardRequest());

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getAllQuotes());
        Assertions.assertFalse(result.getAllQuotes().isEmpty());
        Assertions.assertNotNull(result.getQuotePrices());
        Assertions.assertNotNull(result.getQuotePrices().getData());

        verify(findQuoteCommand, atMostOnce()).bySpecification(any(Specification.class));
    }

}
