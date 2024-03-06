package br.com.events.band.business.use_case.music;

import br.com.events.band.data.io.music.criteria.MusicCriteria;
import br.com.events.band.data.io.music.response.MusicResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindBandMusicsUseCase {

    Page<MusicResponse> execute(String bandUuid, MusicCriteria criteria, Pageable pageable);
}
