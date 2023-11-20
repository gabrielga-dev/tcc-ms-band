package br.com.events.band.newer.adapter.repository.jpa;

import br.com.events.band.newer.adapter.repository.MusicianRepository;
import br.com.events.band.newer.data.model.table.MusicianTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface makes every needed communication to the database at the musician table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface MusicianJpaRepository extends MusicianRepository, JpaRepository<MusicianTable, String> {

    Optional<MusicianTable> findByCpf(String cpf);

    boolean existsByCpfAndBandUuidAndActiveTrue(String cpf, String bandUuid);


    boolean existsByUuidAndBandUuid(String uuid, String bandUuid);

    List<MusicianTable> findByBandUuidAndActiveTrue(String bandUuid);

    Optional<MusicianTable> findByUuidAndBand_UuidAndActiveTrue(String musicianUuid, String bandUuid);

    @Query(
            "SELECT musician " +
                    "FROM MusicianTable musician JOIN musician.address address " +
                    "WHERE (musician.active = :active) " +
                    "AND ((:name IS NOT NULL) OR (musician.firstName LIKE CONCAT('%',:name,'%')) OR (musician.lastName LIKE CONCAT('%',:name,'%'))) " +
                    "AND ((:cpf IS NOT NULL) OR (musician.cpf = :cpf)) " +
                    "AND ((:email IS NOT NULL) OR (musician.email = :email)) " +
                    "AND ((:street IS NOT NULL) OR (address.street = :street)) " +
                    "AND ((:neighbour IS NOT NULL) OR (address.neighbour = :neighbour)) " +
                    "AND ((:complement IS NOT NULL) OR (address.complement = :complement)) " +
                    "AND ((:cityId IS NOT NULL) OR (address.city = :cityId)) " +
                    "AND ((:stateIso IS NOT NULL) OR (address.state = :stateIso)) " +
                    "AND ((:countryIso IS NOT NULL) OR (address.country = :countryIso)) " +
                    "AND ((:zipCode IS NOT NULL) OR (address.zipCode = :zipCode))"
    )
    Page<MusicianTable> findByCriteria(
            @Param("active") boolean active,
            @Param("name") String name,
            @Param("cpf") String cpf,
            @Param("email") String email,
            @Param("street") String street,
            @Param("neighbour") String neighbour,
            @Param("complement") String complement,
            @Param("cityId") Long cityId,
            @Param("stateIso") String stateIso,
            @Param("countryIso") String countryIso,
            @Param("zipCode") String zipCode,
            Pageable pageable
    );
}
