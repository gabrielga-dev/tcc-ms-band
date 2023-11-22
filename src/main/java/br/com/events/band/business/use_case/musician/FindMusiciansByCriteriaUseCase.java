package br.com.events.band.business.use_case.musician;

import br.com.events.band.data.io.musician.criteria.MusicianCriteria;
import br.com.events.band.data.io.musician.response.MusicianWithAddressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindMusiciansByCriteriaUseCase {

    Page<MusicianWithAddressResponse> execute(Pageable pageable, MusicianCriteria criteria);
}
