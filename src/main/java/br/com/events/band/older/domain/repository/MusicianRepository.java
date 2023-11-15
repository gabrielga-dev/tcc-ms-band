package br.com.events.band.older.domain.repository;

import br.com.events.band.newer.data.table.MusicianTable;
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
public interface MusicianRepository extends JpaRepository<MusicianTable, String> {

    boolean existsByCpfAndBandUuidAndActiveTrue(String cpf, String bandUuid);

    Optional<MusicianTable> findByCpf(String cpf);

    boolean existsByUuidAndBandUuid(String uuid, String bandUuid);

    List<MusicianTable> findByBandUuidAndActiveTrue(String bandUuid);

    Optional<MusicianTable> findByUuidAndBand_UuidAndActiveTrue(String musicianUuid, String bandUuid);
}
