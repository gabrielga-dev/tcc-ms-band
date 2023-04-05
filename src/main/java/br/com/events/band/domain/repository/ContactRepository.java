package br.com.events.band.domain.repository;

import br.com.events.band.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface makes every needed communication to the database at the contact table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    List<Contact> findByBandUuid(String bandUuid);
}
