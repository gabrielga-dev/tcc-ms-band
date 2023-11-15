package br.com.events.band.older.infrastructure.useCase.address;

import br.com.events.band.newer.data.table.addresses.MusicianAddressTable;
import br.com.events.band.older.domain.io._new.address.response.AddressResponse;

public interface BuildAddressResponseUseCase {

    AddressResponse execute(MusicianAddressTable address);
}
