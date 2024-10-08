package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.musician.MusicianTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MusicianRepository {

    Optional<MusicianTable> findByCpf(String musicianCpf);

    MusicianTable save(MusicianTable musicianTable);

    Optional<MusicianTable> findById(String musicianUuid);

    List<MusicianTable> findByUuidInList(List<String> musicianUuid);


    Page<MusicianTable> findByCriteria(
            boolean active,
            String name,
            String cpf,
            String email,
            Long cityId,
            String stateIso,
            String countryIso,
            String zipCode,
            Pageable pageable
    );
}
