package br.com.events.band.business.command.music;

import br.com.events.band.adapter.repository.MusicRepository;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.music.criteria.MusicCriteria;
import br.com.events.band.data.model.table.music.MusicTable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindMusicCommand {

    private final MusicRepository musicRepository;

    public Optional<MusicTable> byUuid(String musicUuid) {
        return this.musicRepository.findById(musicUuid);
    }
    public List<MusicTable> byUuids(List<String> musicUuids) {
        return this.musicRepository.findAllByUuids(musicUuids)
                .stream()
                .filter(MusicTable::isActive)
                .collect(Collectors.toList());
    }

    public Page<MusicTable> byBandUuidAndCriteria(String bandUuid, MusicCriteria criteria, Pageable pageable) {
        return this.musicRepository.findByCriteria(
                AuthUtil.getAuthenticatedPersonUuid(),
                bandUuid,
                criteria.getName(),
                criteria.getAuthor(),
                criteria.getArtist(),
                pageable
        );
    }

    public Page<MusicTable> byCriteria(MusicCriteria criteria, Pageable pageable) {
        return this.musicRepository.findByCriteria(
                criteria.getName(),
                criteria.getAuthor(),
                criteria.getArtist(),
                pageable
        );
    }
}
