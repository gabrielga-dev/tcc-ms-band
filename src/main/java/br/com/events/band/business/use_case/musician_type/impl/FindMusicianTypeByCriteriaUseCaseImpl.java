package br.com.events.band.business.use_case.musician_type.impl;

import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.business.use_case.musician_type.FindMusicianTypeByCriteriaUseCase;
import br.com.events.band.data.io.musician_type.criteria.MusicianTypeCriteria;
import br.com.events.band.data.io.musician_type.response.MusicianTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMusicianTypeByCriteriaUseCaseImpl implements FindMusicianTypeByCriteriaUseCase {

    private final FindMusicianTypeCommand findMusicianTypeCommand;

    @Override
    public Page<MusicianTypeResponse> findByCriteria(Pageable pageable, MusicianTypeCriteria criteria) {
        var page = findMusicianTypeCommand.byCriteria(criteria, pageable);
        return page.map(MusicianTypeResponse::new);
    }
}
