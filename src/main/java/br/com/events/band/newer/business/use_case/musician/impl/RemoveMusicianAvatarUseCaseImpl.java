package br.com.events.band.newer.business.use_case.musician.impl;

import br.com.events.band.newer.business.command.musician.FindMusicianCommand;
import br.com.events.band.newer.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.newer.business.command.musician.SaveMusicianCommand;
import br.com.events.band.newer.business.use_case.musician.RemoveMusicianAvatarUseCase;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.newer.core.exception.musician.MusicianHasAnAccountException;
import br.com.events.band.newer.core.util.AuthUtil;
import br.com.events.band.newer.data.io.person.response.PersonResponse;
import br.com.events.band.newer.data.model.table.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveMusicianAvatarUseCaseImpl implements RemoveMusicianAvatarUseCase {

    private final FindMusicianCommand findMusicianCommand;
    private final FindPersonMusicianCommand findPersonMusicianCommand;
    private final SaveMusicianCommand saveMusicianCommand;

    @Override
    public void execute(String musicianUuid) {
        var musician = findMusicianCommand.byUuid(musicianUuid).orElseThrow(MusicianDoesNotExistException::new);
        findPersonMusicianCommand.byCpf(musician.getCpf())
                .ifPresentOrElse(
                        this::isSamePerson,
                        () -> this.isBandOwner(musician)
                );

        musician.removeProfilePicture();

        saveMusicianCommand.execute(musician);
    }

    private void isSamePerson(PersonResponse musician) {
        if (!musician.getUuid().equals(AuthUtil.getAuthenticatedPersonUuid())) {
            throw new MusicianHasAnAccountException();
        }
    }

    private void isBandOwner(MusicianTable musician) {
        if (!musician.getBandThatInserted().getOwnerUuid().equals(AuthUtil.getAuthenticatedPersonUuid())) {
            throw new BandOwnerException();
        }
    }
}
