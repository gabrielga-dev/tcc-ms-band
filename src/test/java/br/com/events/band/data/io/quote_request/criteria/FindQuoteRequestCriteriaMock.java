package br.com.events.band.data.io.quote_request.criteria;

import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FindQuoteRequestCriteriaMock {

    public static FindQuoteRequestCriteria build() {
        return new FindQuoteRequestCriteria(
                List.of(QuoteRequestStatusType.NON_ANSWERED),
                DateUtil.localDateTimeToMilliseconds(LocalDateTime.now()),
                DateUtil.localDateTimeToMilliseconds(LocalDateTime.now())
        );
    }
}
