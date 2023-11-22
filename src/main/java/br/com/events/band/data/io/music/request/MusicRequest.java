package br.com.events.band.data.io.music.request;

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
public class MusicRequest {

    @Size(min = 2, max = 45, message = "O campo do nome da música deve ter entre 2 e 45 caracteres.")
    private String name;

    @Size(min = 2, max = 60, message = "O campo do nome do autor música deve ter entre 2 e 45 caracteres.")
    private String author;

    @Size(min = 2, max = 60, message = "O campo do nome do autor música deve ter entre 2 e 45 caracteres.")
    private String artist;

    @Size(max = 1000, message = "O campo de observação da música deve ter, no máximo 1000 caracteres.")
    private String observation;
}
