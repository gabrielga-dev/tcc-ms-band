package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.band.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.band.business.use_case.quote_request.DeclineQuoteRequestUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.quote_request.QuoteRequestNotFound;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.quote_request.request.DeclineQuoteRequestMsEventRequest;
import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DeclineQuoteRequestUseCaseImpl implements DeclineQuoteRequestUseCase {

    private final FindQuoteRequestCommand findQuoteRequestCommand;
    private final SaveQuoteRequestCommand saveQuoteRequestCommand;
    private final MsEventFeign msEventFeign;

    @Override
    public void execute(String quoteRequestUuid) {
        var quoteRequest = findQuoteRequestCommand.findByUuidOrThrow(quoteRequestUuid);
        if (!Objects.equals(AuthUtil.getAuthenticatedPersonUuid(), quoteRequest.getBand().getOwnerUuid())) {
            throw new BandOwnerException();
        }
        if (!Objects.equals(QuoteRequestStatusType.NON_ANSWERED, quoteRequest.getStatus())) {
            throw new QuoteRequestNotFound();
        }
        quoteRequest.setStatus(QuoteRequestStatusType.DECLINED);
        quoteRequest.setUpdateDate(LocalDateTime.now());
        quoteRequest = saveQuoteRequestCommand.execute(quoteRequest);

        var declineQuoteRequestRequest = new DeclineQuoteRequestMsEventRequest(quoteRequest);
        msEventFeign.declineQuoteRequest(quoteRequest.getOriginQuoteRequestUuid(), declineQuoteRequestRequest);
    }
}
