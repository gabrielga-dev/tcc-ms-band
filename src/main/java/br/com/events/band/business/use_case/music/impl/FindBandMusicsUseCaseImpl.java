package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.use_case.music.FindBandMusicsUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.music.response.MusicResponse;
import br.com.events.band.data.model.ActionableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindBandMusicsUseCaseImpl implements FindBandMusicsUseCase {

    private final FindBandCommand findBandCommand;

    @Override
    public List<MusicResponse> execute(String bandUuid) {
        var band = findBandCommand.byUuid(bandUuid)
                .orElseThrow(BandNonExistenceException::new);
        if (AuthUtil.getAuthenticatedPersonUuid().equals(band.getOwnerUuid())) {
            return band.getContributedMusics()
                    .stream()
                    .map(MusicResponse::new)
                    .collect(Collectors.toList());
        }
        return band.getContributedMusics()
                .stream()
                .filter(ActionableTable::isActive)
                .map(MusicResponse::new)
                .collect(Collectors.toList());
    }
}
