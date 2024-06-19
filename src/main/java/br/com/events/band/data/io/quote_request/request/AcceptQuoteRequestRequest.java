package br.com.events.band.data.io.quote_request.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcceptQuoteRequestRequest {

    @NotNull(message = "O valor final deve ser não nulo.")
    @Min(value = 0, message = "O valor final deve um valor positivo.")
    private BigDecimal finalValue;

    @Size(max = 1500, message = "O campo de observações deve ter, no máximo, 1500 caracteres.")
    private String observation;

    @NotNull(message = "A seleção de músicos deve ser não nula.")
    private List<String> musicianUuids;
}
