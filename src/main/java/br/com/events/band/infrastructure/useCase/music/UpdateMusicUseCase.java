package br.com.events.band.infrastructure.useCase.music;

import br.com.events.band.domain.io.music.create.in.CreateMusicForm;
import br.com.events.band.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.domain.io.music.update.UpdateMusicMapper;
import br.com.events.band.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

public interface UpdateMusicUseCase extends UseCaseBase<UpdateMusicUseCaseForm, CreateMusicResult> {

    default CreateMusicResult execute(String musicUuid, CreateMusicForm music){
        var mappedForm = UpdateMusicMapper.from(musicUuid, music);
        return this.execute(mappedForm);
    }
}
