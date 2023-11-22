package br.com.events.band.core.exception.musician;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.UnprocessableEntityException;

public class MusicianBelowAgeException extends UnprocessableEntityException {

    public MusicianBelowAgeException(int currentAge, int minimumAge) {
        super(
                "O músico não possui a idade mínima!",
                "Atualmente, o musico em questão possui " + currentAge + " anos, mas é preciso ter, pelo menos" +
                        minimumAge + " anos para estar na plataforma."
        );
    }
}
