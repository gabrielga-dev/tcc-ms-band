package br.com.events.band.newer.adapter.reporitory.jpa;

import br.com.events.band.newer.adapter.reporitory.ContactRepository;
import br.com.events.band.newer.data.table.ContactTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface makes every needed communication to the database at the contact table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface ContactJpaRepository extends ContactRepository, JpaRepository<ContactTable, String> {

    List<ContactTable> findByBandUuid(String bandUuid);

    Optional<ContactTable> findByUuidAndBandUuid(String contactUuid, String bandUuid);
}
