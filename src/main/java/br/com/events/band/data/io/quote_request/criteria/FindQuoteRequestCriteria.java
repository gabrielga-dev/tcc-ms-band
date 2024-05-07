package br.com.events.band.data.io.quote_request.criteria;

import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindQuoteRequestCriteria {

    private List<QuoteRequestStatusType> statuses = new ArrayList<>();
    private Long startDate;
    private Long endDate;

    @JsonIgnore
    public List<QuoteRequestStatusType> getStatuses() {
        if (Objects.isNull(statuses) || statuses.isEmpty()) {
            return null;
        }
        return statuses;
    }

    @JsonIgnore
    public LocalDateTime getStartDate() {
        if (Objects.isNull(startDate)) {
            return null;
        }
        return DateUtil.millisecondsToLocalDateTime(startDate);
    }

    @JsonIgnore
    public LocalDateTime getEndDate() {
        if (Objects.isNull(endDate)) {
            return null;
        }
        return DateUtil.millisecondsToLocalDateTime(endDate);
    }
}
