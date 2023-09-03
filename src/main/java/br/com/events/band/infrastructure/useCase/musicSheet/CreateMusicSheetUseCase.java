package br.com.events.band.infrastructure.useCase.musicSheet;

import br.com.events.band.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import br.com.events.band.domain.io.musicSheet.create.useCase.in.CreateSheetMusicUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;
import org.springframework.web.multipart.MultipartFile;

public interface CreateMusicSheetUseCase extends UseCaseBase<CreateSheetMusicUseCaseForm, CreateMusicSheetResult> {

    default CreateMusicSheetResult execute(String musicUuid, String observation, MultipartFile file){
        var mappedForm = CreateSheetMusicUseCaseForm
                .builder()
                .musicUuid(musicUuid)
                .observation(observation)
                .file(file)
                .build();
        return this.execute(mappedForm);
    }
}
