package br.com.events.band.data.io.musician_type.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MusicianTypeRequest {

    @NotNull(message = "O campo de tipo de músico não pode ser nulo.")
    private String uuid;
}
