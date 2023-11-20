package br.com.events.band.newer.business.use_case.musician.impl;

import br.com.events.band.newer.business.command.musician.FindMusicianCommand;
import br.com.events.band.newer.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.newer.business.command.musician.SaveMusicianCommand;
import br.com.events.band.newer.business.use_case.musician.DeleteMusiciansUseCase;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.exception.musician.MusicianExistsException;
import br.com.events.band.newer.core.exception.musician.MusicianHasAnAccountException;
import br.com.events.band.newer.core.util.AuthUtil;
import br.com.events.band.newer.data.io.person.response.PersonResponse;
import br.com.events.band.newer.data.model.table.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMusiciansUseCaseImpl implements DeleteMusiciansUseCase {

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
    }

    private void deleteWhenMusicianIsPerson(PersonResponse person, MusicianTable musician) {
        if (!AuthUtil.getAuthenticatedPersonUuid().equals(person.getUuid())) {
            throw new MusicianHasAnAccountException();
        }
        this.delete(musician);
    }

    private void deleteWhenMusicianIsNotPerson(MusicianTable musician) {
        if (!AuthUtil.getAuthenticatedPersonUuid().equals(musician.getBandThatInserted().getOwnerUuid())) {
            throw new BandOwnerException();
        }
        this.delete(musician);
    }

    private void delete(MusicianTable musician) {
        musician.toggleActivity();
        saveMusicianCommand.execute(musician);
    }
}
