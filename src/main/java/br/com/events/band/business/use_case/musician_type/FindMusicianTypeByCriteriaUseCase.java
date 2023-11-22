package br.com.events.band.business.use_case.musician_type;

import br.com.events.band.data.io.musician_type.criteria.MusicianTypeCriteria;
import br.com.events.band.data.io.musician_type.response.MusicianTypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindMusicianTypeByCriteriaUseCase {

    Page<MusicianTypeResponse> findByCriteria(Pageable pageable, MusicianTypeCriteria criteria);
}
