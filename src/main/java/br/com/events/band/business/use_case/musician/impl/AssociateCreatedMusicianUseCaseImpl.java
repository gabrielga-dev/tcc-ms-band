package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.use_case.musician.AssociateCreatedMusicianUseCase;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociateCreatedMusicianUseCaseImpl implements AssociateCreatedMusicianUseCase {

    private final FindBandCommand findBandCommand;
    private final FindMusicianCommand findMusicianCommand;

    @Override
    public UuidHolderDTO execute(String bandUuid, String musicianCpf) {
        var band = findBandCommand.byUuidAndOwnerUuid(bandUuid, AuthUtil.getAuthenticatedPersonUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.isActive()) {
            throw new BandNonExistenceException();
        }

        var musician = findMusicianCommand.byCpf(musicianCpf).orElseThrow(MusicianDoesNotExistException::new);

        band.associate(musician);

        return new UuidHolderDTO(musician.getUuid());
    }
}
