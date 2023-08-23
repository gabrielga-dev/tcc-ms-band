package br.com.events.band.domain.io.music.update.in;

import br.com.events.band.domain.io.music.create.in.CreateMusicForm;
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
public class UpdateMusicUseCaseForm extends CreateMusicForm {

    private String musicUuid;
}
