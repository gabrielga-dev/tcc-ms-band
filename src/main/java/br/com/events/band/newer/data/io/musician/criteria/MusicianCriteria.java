package br.com.events.band.newer.data.io.musician.criteria;

import br.com.events.band.newer.data.io.address.request.AddressRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MusicianCriteria {

    private final String name;
    private final String cpf;
    private final String email;
    private final boolean active = true;
    private final AddressRequest address;
}
