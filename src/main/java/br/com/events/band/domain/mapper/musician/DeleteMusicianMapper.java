package br.com.events.band.domain.mapper.musician;

import br.com.events.band.domain.io.musician.delete.useCase.in.DeleteMusicianUseCaseForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeleteMusicianMapper {

    public static DeleteMusicianUseCaseForm from(String bandUuid, String musicianUuid){
        return DeleteMusicianUseCaseForm
                .builder()
                .bandUuid(bandUuid)
                .musicianUuid(musicianUuid)
                .build();
    }
}
