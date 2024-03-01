package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.band_musician.DeleteAllBandMusicianAssociationsCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.use_case.musician.DeactivateMusiciansUseCase;
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
public class DeactivateMusiciansUseCaseImpl implements DeactivateMusiciansUseCase {

    private final FindMusicianCommand findMusicianCommand;
    private final FindPersonMusicianCommand findPersonMusicianCommand;
    private final SaveMusicianCommand saveMusicianCommand;
    private final DeleteAllBandMusicianAssociationsCommand deleteAllBandMusicianAssociationsCommand;

    @Override
    public void execute(String musicianUuid) {
        var musician = findMusicianCommand.byUuid(musicianUuid).orElseThrow(MusicianExistsException::new);

        findPersonMusicianCommand.byCpf(musician.getCpf())
                .ifPresentOrElse(
                        this::deleteWhenMusicianIsPerson,
                        () -> this.deleteWhenMusicianIsNotPerson(musician)
                );
        musician.deactivate();
        saveMusicianCommand.execute(musician);
        deleteAllBandMusicianAssociationsCommand.execute(musician.getUuid());
    }

    private void deleteWhenMusicianIsPerson(PersonResponse person) {
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
