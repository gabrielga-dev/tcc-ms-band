package br.com.events.band.data.io.musician.criteria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MusicianCriteria {

    private final String name;
    private final String cpf;
    private final String email;
    private final boolean active = true;
    private final Long cityId;
    private final String stateIso;
    private final String countryIso;
    private final String zipCode;
}
