package br.com.events.band.older.domain.io.music.update.in;

import br.com.events.band.newer.data.io.music.request.MusicRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMusicUseCaseForm extends MusicRequest {

    private String musicUuid;
}
