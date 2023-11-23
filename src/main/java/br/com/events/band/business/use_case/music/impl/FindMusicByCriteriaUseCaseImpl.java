package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.use_case.music.FindMusicByCriteriaUseCase;
import br.com.events.band.data.io.music.criteria.MusicCriteria;
import br.com.events.band.data.io.music.response.MusicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMusicByCriteriaUseCaseImpl implements FindMusicByCriteriaUseCase {

    private final FindMusicCommand findMusicCommand;

    @Override
    public Page<MusicResponse> execute(MusicCriteria criteria, Pageable pageable) {
        var rawPage = findMusicCommand.byCriteria(criteria, pageable);
        return rawPage.map(MusicResponse::new);
    }
}
