package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.use_case.musician.FindMusiciansByCriteriaUseCase;
import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.data.io.musician.criteria.MusicianCriteria;
import br.com.events.band.data.io.musician.response.MusicianWithAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMusiciansByCriteriaUseCaseImpl implements FindMusiciansByCriteriaUseCase {

    private final FindMusicianCommand findMusicianCommand;
    private final BuildAddressResponseCommand buildAddressResponseCommand;

    @Override
    public Page<MusicianWithAddressResponse> execute(Pageable pageable, MusicianCriteria criteria) {
        var musicians = findMusicianCommand.byCriteria(pageable, criteria);

        return musicians.map(
                musician -> {
                    var musicianAddress = buildAddressResponseCommand.execute(musician.getAddress());
                    return new MusicianWithAddressResponse(musician, musicianAddress);
                }
        );
    }
}
