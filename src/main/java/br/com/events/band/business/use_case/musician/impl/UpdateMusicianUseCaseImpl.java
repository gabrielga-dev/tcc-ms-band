package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.use_case.musician.UpdateMusicianUseCase;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.musician.request.MusicianRequest;
import br.com.events.band.data.model.table.MusicianTable;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.core.exception.musician.MusicianHasAnAccountException;
import br.com.events.band.data.io.person.response.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMusicianUseCaseImpl implements UpdateMusicianUseCase {

    private final FindMusicianCommand findMusicianCommand;
    private final FindPersonMusicianCommand findPersonMusicianCommand;
    private final SaveMusicianCommand saveMusicianCommand;

    @Override
    public void execute(String musicianUuid, MusicianRequest request) {
        var musician = findMusicianCommand.byUuid(musicianUuid).orElseThrow(MusicianDoesNotExistException::new);

        findPersonMusicianCommand.byCpf(musician.getCpf())
                .ifPresentOrElse(
                        this::isSamePerson,
                        () -> this.isMusiciansBandOwner(musician)
                );
        musician.update(request);
        saveMusicianCommand.execute(musician);
    }

    private void isSamePerson(PersonResponse person) {
        if (!AuthUtil.getAuthenticatedPersonUuid().equals(person.getUuid())) {
            throw new MusicianHasAnAccountException();
        }
    }

    private void isMusiciansBandOwner(MusicianTable musician) {
        if (!AuthUtil.getAuthenticatedPersonUuid().equals(musician.getBandThatInserted().getOwnerUuid())) {
            throw new BandOwnerException();
        }
    }
}
