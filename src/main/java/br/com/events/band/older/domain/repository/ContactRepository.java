package br.com.events.band.older.domain.repository;

import br.com.events.band.newer.data.table.ContactTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface makes every needed communication to the database at the contact table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface ContactRepository extends JpaRepository<ContactTable, String> {

    List<ContactTable> findByBandUuid(String bandUuid);
}
