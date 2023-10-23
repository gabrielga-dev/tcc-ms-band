package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io._new.musician.form.MusicianForm;

public interface UpdateMusicianUseCase {

    void execute(String bandUuid, String musicianUuid, MusicianForm musicianForm);
}
