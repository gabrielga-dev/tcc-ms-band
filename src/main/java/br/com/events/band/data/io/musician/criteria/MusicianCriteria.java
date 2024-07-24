package br.com.events.band.data.io.musician.criteria;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MusicianCriteria {

    private final String name;
    private final String cpf;
    private final String email;
    @JsonProperty("active")
    private static final boolean ACTIVE = true;
    private final Long cityId;
    private final String stateIso;
    private final String countryIso;
    private final String zipCode;

    public boolean isActive() {
        return ACTIVE;
    }
}
