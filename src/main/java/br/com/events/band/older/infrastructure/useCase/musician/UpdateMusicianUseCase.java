package br.com.events.band.older.infrastructure.useCase.musician;

import br.com.events.band.older.domain.io._new.musician.form.MusicianForm;

public interface UpdateMusicianUseCase {

    void execute(String bandUuid, String musicianUuid, MusicianForm musicianForm);
}
