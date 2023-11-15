package br.com.events.band.newer.adapter.reporitory;

import br.com.events.band.newer.data.table.ContactTable;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    ContactTable save(ContactTable bandTable);

    void deleteById(String uuid);

    List<ContactTable> findByBandUuid(String bandUuid);

    Optional<ContactTable> findById(String uuid);
    Optional<ContactTable> findByUuidAndBandUuid(String contactUuid, String bandUuid);
}
