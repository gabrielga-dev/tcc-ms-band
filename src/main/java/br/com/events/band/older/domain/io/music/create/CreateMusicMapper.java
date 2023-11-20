package br.com.events.band.older.domain.io.music.create;

import br.com.events.band.newer.data.model.table.BandTable;
import br.com.events.band.newer.data.model.table.MusicTable;
import br.com.events.band.newer.data.io.music.request.MusicRequest;
import br.com.events.band.older.domain.io.music.create.in.CreateMusicUseCaseForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateMusicMapper {

    public static CreateMusicUseCaseForm from(String bandUuid, MusicRequest createMusicForm){
        return CreateMusicUseCaseForm
                .builder()
                .name(createMusicForm.getName())
                .observation(createMusicForm.getObservation())
                .bandUuid(bandUuid)
                .build();
    }

    public static MusicTable from(CreateMusicUseCaseForm param, BandTable band) {
        var toReturn = new MusicTable();

        toReturn.setName(param.getName());
        toReturn.setObservation(param.getObservation());
        toReturn.setBand(band);
        toReturn.setCreationDate(LocalDateTime.now());

        return toReturn;
    }
}
