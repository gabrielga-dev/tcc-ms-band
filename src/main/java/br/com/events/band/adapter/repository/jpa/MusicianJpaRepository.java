package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.MusicianRepository;
import br.com.events.band.data.model.table.musician.MusicianTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface makes every needed communication to the database at the musician table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface MusicianJpaRepository extends MusicianRepository, JpaRepository<MusicianTable, String> {

    Optional<MusicianTable> findByCpf(String cpf);

    @Query(
            "SELECT musician " +
                    "FROM MusicianTable musician JOIN musician.address address " +
                    "WHERE (musician.active = :active) " +
                    "AND ((:name IS NULL) OR (musician.firstName LIKE CONCAT('%',:name,'%')) OR (musician.lastName LIKE CONCAT('%',:name,'%'))) " +
                    "AND ((:cpf IS NULL) OR (musician.cpf = :cpf)) " +
                    "AND ((:email IS NULL) OR (musician.email LIKE CONCAT('%',:email,'%'))) " +
                    "AND ((:cityId IS NULL) OR (address.city = :cityId)) " +
                    "AND ((:stateIso IS NULL) OR (address.state = :stateIso)) " +
                    "AND ((:countryIso IS NULL) OR (address.country = :countryIso)) " +
                    "AND ((:zipCode IS NULL) OR (address.zipCode = :zipCode))"
    )
    Page<MusicianTable> findByCriteria(
            @Param("active") boolean active,
            @Param("name") String name,
            @Param("cpf") String cpf,
            @Param("email") String email,
            @Param("cityId") Long cityId,
            @Param("stateIso") String stateIso,
            @Param("countryIso") String countryIso,
            @Param("zipCode") String zipCode,
            Pageable pageable
    );
}
