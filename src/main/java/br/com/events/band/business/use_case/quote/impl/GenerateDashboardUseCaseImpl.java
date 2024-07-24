package br.com.events.band.business.use_case.quote.impl;

import br.com.events.band.business.command.quote.FindQuoteCommand;
import br.com.events.band.business.use_case.quote.GenerateDashboardUseCase;
import br.com.events.band.data.io.quote.request.DashboardRequest;
import br.com.events.band.data.io.quote.response.DashboardResponse;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.quote.QuoteTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GenerateDashboardUseCaseImpl implements GenerateDashboardUseCase {

    private final FindQuoteCommand findQuoteCommand;

    private static final String CREATION_DATE_LABEL = "creationDate";

    @Override
    public DashboardResponse execute(DashboardRequest criteria) {
        var quotes = findQuoteCommand.bySpecification(generateSpecification(criteria));
        return new DashboardResponse(quotes);
    }

    private Specification<QuoteTable> generateSpecification(DashboardRequest criteria) {
        return (root, cq, builder) -> {
            cq.distinct(true);

            Join<QuoteTable, QuoteRequestTable> quoteRequestJoin = root.join("quoteRequest", JoinType.LEFT);
            Join<QuoteRequestTable, BandTable> bandJoin = quoteRequestJoin.join("band", JoinType.LEFT);

            var quote = cq.from(QuoteTable.class);
            var predicates = new ArrayList<Predicate>();

            predicates.add(builder.equal(bandJoin.get("uuid"), criteria.getBandUuid()));

            if (Objects.nonNull(criteria.getStartDate()) && Objects.nonNull(criteria.getEndDate())) {
                predicates.add(
                        builder.between(
                                quote.get(CREATION_DATE_LABEL),
                                criteria.getStartDate(),
                                criteria.getEndDate()
                        )
                );
            } else {
                if (Objects.nonNull(criteria.getStartDate())) {
                    predicates.add(builder.greaterThanOrEqualTo(quote.get(CREATION_DATE_LABEL), criteria.getStartDate()));
                }

                if (Objects.nonNull(criteria.getEndDate())) {
                    predicates.add(builder.lessThanOrEqualTo(quote.get(CREATION_DATE_LABEL), criteria.getEndDate()));
                }
            }

            return builder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
