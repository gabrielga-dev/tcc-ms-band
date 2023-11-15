package br.com.events.band.older.infrastructure.useCase.address;

import br.com.events.band.older.domain.entity.address.MusicianAddress;
import br.com.events.band.older.domain.io._new.address.response.AddressResponse;

public interface BuildAddressResponseUseCase {

    AddressResponse execute(MusicianAddress address);
}
