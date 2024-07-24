package br.com.events.band.data.io.quote_request.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicianTypeQuoteRequestRequest implements Serializable {

    @NotNull(message = "Informe um tipo de músico existente")
    private String musicianTypeUuid;

    @Min(value = 0, message = "A quantidade dos músicos deve ser válida")
    private Integer quantity;

    @Size(max = 500, message = "O campo de observação deve ter, no máximo, 500 caracteres")
    private String observation;

}
