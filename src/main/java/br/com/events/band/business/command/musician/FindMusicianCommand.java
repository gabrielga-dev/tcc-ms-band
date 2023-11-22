package br.com.events.band.business.command.musician;

import br.com.events.band.adapter.repository.MusicianRepository;
import br.com.events.band.data.io.musician.criteria.MusicianCriteria;
import br.com.events.band.data.model.table.musician.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindMusicianCommand {

    private final MusicianRepository musicianRepository;

    public Optional<MusicianTable> byUuid(String musicianUuid) {
        return musicianRepository.findById(musicianUuid);
    }

    public Optional<MusicianTable> byCpf(String musicianCpf) {
        return musicianRepository.findByCpf(musicianCpf);
    }

    public Page<MusicianTable> byCriteria(Pageable pageable, MusicianCriteria criteria) {
        return musicianRepository.findByCriteria(
                criteria.isActive(),
                criteria.getName(),
                criteria.getCpf(),
                criteria.getEmail(),
                criteria.getCityId(),
                criteria.getStateIso(),
                criteria.getCountryIso(),
                criteria.getZipCode(),
                pageable
        );
    }
}
