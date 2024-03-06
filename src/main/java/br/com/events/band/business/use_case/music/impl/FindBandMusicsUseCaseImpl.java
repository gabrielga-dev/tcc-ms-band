package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.use_case.music.FindBandMusicsUseCase;
import br.com.events.band.data.io.music.criteria.MusicCriteria;
import br.com.events.band.data.io.music.response.MusicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandMusicsUseCaseImpl implements FindBandMusicsUseCase {

    private final FindMusicCommand findMusicCommand;

    @Override
    public Page<MusicResponse> execute(String bandUuid, MusicCriteria criteria, Pageable pageable) {
        return findMusicCommand.byBandUuidAndCriteria(bandUuid, criteria, pageable)
                .map(MusicResponse::new);
    }
}
