package br.com.events.band.older.domain.mapper.musician;

import br.com.events.band.older.domain.io.musician.delete.in.DeleteMusicianUseCaseForm;
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
