package br.com.events.band.data.io.quote_request.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicQuoteRequestRequest {

    @NotNull(message = "Informe uma música existente")
    private String musicUuid;

    @Size(max = 500, message = "O campo de observação deve ter, no máximo, 500 caracteres")
    private String observation;

    @Min(value = 0, message = "A ordem das músicas devem ser válidas")
    private Integer order;
}
