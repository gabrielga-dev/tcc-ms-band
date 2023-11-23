package br.com.events.band.business.use_case.music;

import br.com.events.band.data.io.music.criteria.MusicCriteria;
import br.com.events.band.data.io.music.response.MusicResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindMusicByCriteriaUseCase {
    Page<MusicResponse> execute(MusicCriteria criteria, Pageable pageable);
}
