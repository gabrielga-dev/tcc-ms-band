package br.com.events.band.older.infrastructure.useCase.music;

import br.com.events.band.older.domain.io.music.create.in.CreateMusicForm;
import br.com.events.band.older.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.older.domain.io.music.update.UpdateMusicMapper;
import br.com.events.band.older.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;

public interface UpdateMusicUseCase extends UseCaseBase<UpdateMusicUseCaseForm, CreateMusicResult> {

    default CreateMusicResult execute(String musicUuid, CreateMusicForm music){
        var mappedForm = UpdateMusicMapper.from(musicUuid, music);
        return this.execute(mappedForm);
    }
}
