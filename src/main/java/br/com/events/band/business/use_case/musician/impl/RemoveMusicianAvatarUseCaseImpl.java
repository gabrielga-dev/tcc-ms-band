package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.musician.RemoveMusicianAvatarUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.core.exception.musician.MusicianHasAnAccountException;
import br.com.events.band.data.io.person.response.PersonResponse;
import br.com.events.band.data.model.table.musician.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveMusicianAvatarUseCaseImpl implements RemoveMusicianAvatarUseCase {

    private final AuthService authService;
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
        if (!musician.getCpf().equals(authService.getAuthenticatedPerson().getCpf())) {
            throw new MusicianHasAnAccountException();
        }
    }

    private void isBandOwner(MusicianTable musician) {
        if (!musician.getBandThatInserted().getOwnerUuid().equals(authService.getAuthenticatedPersonUuid())) {
            throw new BandOwnerException();
        }
    }
}
