package br.com.events.band.domain.io.music.create.in;

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
public class CreateMusicForm {

    @Size(min = 2, max = 45, message = "O campo do nome da música deve ter entre 2 e 45 caracteres.")
    private String name;

    @Size(max = 1000, message = "O campo de observação da música deve ter, no máximo 1000 caracteres.")
    private String observation;
}
