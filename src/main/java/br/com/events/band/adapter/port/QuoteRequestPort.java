package br.com.events.band.adapter.port;

import br.com.events.band.data.io.quote_request.response.complete.CompleteBriefQuoteRequestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(tags = "Quote Request Controller")
public interface QuoteRequestPort {

    @ApiOperation(value = "Find a quote request by its uuid")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<CompleteBriefQuoteRequestResponse> findByUuid(String quoteRequestUuid);

    @ApiOperation(value = "Decline a  quote request")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> decline(String quoteRequestUuid);
}
