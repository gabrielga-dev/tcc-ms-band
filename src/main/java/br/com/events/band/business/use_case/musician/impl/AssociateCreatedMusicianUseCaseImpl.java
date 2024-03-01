package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.band.SaveBandCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.use_case.musician.AssociateCreatedMusicianUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.music.MusicianAlreadyLinkedToBandException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociateCreatedMusicianUseCaseImpl implements AssociateCreatedMusicianUseCase {

    private final FindBandCommand findBandCommand;
    private final FindMusicianCommand findMusicianCommand;
    private final SaveBandCommand saveBandCommand;

    @Override
    public UuidHolderDTO execute(String bandUuid, String musicianCpf) {
        var band = findBandCommand.byUuidAndOwnerUuid(bandUuid, AuthUtil.getAuthenticatedPersonUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.isActive()) {
            throw new BandNonExistenceException();
        }

        var musician = findMusicianCommand.byCpf(musicianCpf).orElseThrow(MusicianDoesNotExistException::new);

        if (musician.isAssociatedWith(band)) {
            throw new MusicianAlreadyLinkedToBandException();
        }

        band.associate(musician);

        saveBandCommand.execute(band);

        return new UuidHolderDTO(musician.getUuid());
    }
}
