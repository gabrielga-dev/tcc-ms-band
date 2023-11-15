package br.com.events.band.older.domain.io.music.create;

import br.com.events.band.newer.data.table.BandTable;
import br.com.events.band.older.domain.entity.Music;
import br.com.events.band.older.domain.io.music.create.in.CreateMusicForm;
import br.com.events.band.older.domain.io.music.create.in.CreateMusicUseCaseForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateMusicMapper {

    public static CreateMusicUseCaseForm from(String bandUuid, CreateMusicForm createMusicForm){
        return CreateMusicUseCaseForm
                .builder()
                .name(createMusicForm.getName())
                .observation(createMusicForm.getObservation())
                .bandUuid(bandUuid)
                .build();
    }

    public static Music from(CreateMusicUseCaseForm param, BandTable band) {
        var toReturn = new Music();

        toReturn.setName(param.getName());
        toReturn.setObservation(param.getObservation());
        toReturn.setBand(band);
        toReturn.setCreationDate(LocalDateTime.now());

        return toReturn;
    }
}
