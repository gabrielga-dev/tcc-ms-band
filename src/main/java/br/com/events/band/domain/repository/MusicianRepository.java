package br.com.events.band.domain.repository;

import br.com.events.band.domain.entity.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface makes every needed communication to the database at the musician table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface MusicianRepository extends JpaRepository<Musician, String> {

    boolean existsByCpfAndBandUuid(String cpf, String bandUuid);

    Optional<Musician> findByCpf(String cpf);

    boolean existsByUuidAndBandUuid(String uuid, String bandUuid);

    List<Musician> findByBandUuidAndActiveTrue(String bandUuid);
}
