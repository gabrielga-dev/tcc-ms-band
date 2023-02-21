package br.com.events.band.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.events.band.domain.entity.Band;

/**
 * This interface makes every needed communication to the database at the band table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface BandRepository extends JpaRepository<Band, String> {

}
