package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.quote_request.FindQuoteRequestByUuidUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.data.io.quote_request.response.complete.CompleteBriefQuoteRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FindQuoteRequestByUuidUseCaseImpl implements FindQuoteRequestByUuidUseCase {

    private final AuthService authService;
    private final FindQuoteRequestCommand findQuoteRequestCommand;

    @Override
    public CompleteBriefQuoteRequestResponse execute(String quoteRequestUuid) {
        var quoteRequest = findQuoteRequestCommand.findByUuidOrThrow(quoteRequestUuid);
        if (!Objects.equals(authService.getAuthenticatedPersonUuid(), quoteRequest.getBand().getOwnerUuid())) {
            throw new BandOwnerException();
        }

        return new CompleteBriefQuoteRequestResponse(quoteRequest);
    }
}
