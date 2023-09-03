package br.com.events.band.domain.io.music.create.in;

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
public class CreateMusicUseCaseForm extends CreateMusicForm {

    private String bandUuid;
}
