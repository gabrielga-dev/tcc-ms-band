package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.use_case.musician.ActivateMusiciansUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianExistsException;
import br.com.events.band.core.exception.musician.MusicianHasAnAccountException;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.person.response.PersonResponse;
import br.com.events.band.data.model.table.musician.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivateMusiciansUseCaseImpl implements ActivateMusiciansUseCase {

    private final FindMusicianCommand findMusicianCommand;
    private final FindPersonMusicianCommand findPersonMusicianCommand;
    private final SaveMusicianCommand saveMusicianCommand;

    @Override
    public void execute(String musicianUuid) {
        var musician = findMusicianCommand.byUuid(musicianUuid).orElseThrow(MusicianExistsException::new);

        findPersonMusicianCommand.byCpf(musician.getCpf())
                .ifPresentOrElse(
                        person -> this.deleteWhenMusicianIsPerson(person, musician),
                        () -> this.deleteWhenMusicianIsNotPerson(musician)
                );
        musician.activate();
        saveMusicianCommand.execute(musician);
    }

    private void deleteWhenMusicianIsPerson(PersonResponse person, MusicianTable musician) {
        if (!AuthUtil.getAuthenticatedPerson().getCpf().equals(person.getCpf())) {
            throw new MusicianHasAnAccountException();
        }
    }

    private void deleteWhenMusicianIsNotPerson(MusicianTable musician) {
        if (!AuthUtil.getAuthenticatedPersonUuid().equals(musician.getBandThatInserted().getOwnerUuid())) {
            throw new BandOwnerException();
        }
    }
}
