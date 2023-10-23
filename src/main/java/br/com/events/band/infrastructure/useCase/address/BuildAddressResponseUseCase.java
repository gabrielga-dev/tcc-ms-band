package br.com.events.band.infrastructure.useCase.address;

import br.com.events.band.domain.entity.address.MusicianAddress;
import br.com.events.band.domain.io._new.address.response.AddressResponse;

public interface BuildAddressResponseUseCase {

    AddressResponse execute(MusicianAddress address);
}
