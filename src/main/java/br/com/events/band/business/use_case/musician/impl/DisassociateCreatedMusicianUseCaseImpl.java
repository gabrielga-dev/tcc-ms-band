package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.band_musician.DeleteBandMusicianAssociationCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.use_case.musician.DisassociateCreatedMusicianUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.core.exception.musician.MusicianNotAssociatedWithBandException;
import br.com.events.band.core.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DisassociateCreatedMusicianUseCaseImpl implements DisassociateCreatedMusicianUseCase {

    private final FindBandCommand findBandCommand;
    private final FindMusicianCommand findMusicianCommand;
    private final DeleteBandMusicianAssociationCommand deleteBandMusicianAssociationCommand;

    @Override
    public void execute(String bandUuid, String musicianUuid) {
        var band = findBandCommand.byUuid(bandUuid).orElseThrow(BandNonExistenceException::new);
        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPersonUuid())) {
            throw new BandOwnerException();
        }

        var musician = findMusicianCommand.byUuid(musicianUuid).orElseThrow(MusicianDoesNotExistException::new);
        if (!band.isAssociatedWith(musician)) {
            throw new MusicianNotAssociatedWithBandException();
        }

        var association = band.getAssociation(musician).orElseThrow(MusicianNotAssociatedWithBandException::new);

        deleteBandMusicianAssociationCommand.execute(association);
    }
}
