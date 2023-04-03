package br.com.events.band.domain.repository;

import br.com.events.band.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface makes every needed communication to the database at the band table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
