package br.com.events.band.domain.io.musicSheet.create.rest.in;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSheetMusicRestForm {

    @Size(max = 1000, message = "O campo de observação deve ter, no máximo, 1000 caracteres.")
    private String observation;
}
