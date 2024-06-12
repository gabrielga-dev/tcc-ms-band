package br.com.events.band.data.io.quote.request;

import br.com.events.band.core.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardRequest {

    @NotNull(message = "Selecione alguma banda para gerar os gráficos")
    private String bandUuid;
    private Long startDate;
    private Long endDate;

    @JsonIgnore
    public LocalDateTime getStartDate() {
        return DateUtil.millisecondsToLocalDateTime(this.startDate);
    }

    @JsonIgnore
    public LocalDateTime getEndDate() {
        return DateUtil.millisecondsToLocalDateTime(this.endDate);
    }
}
