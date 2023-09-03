package br.com.events.band.domain.io.musician.delete.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeleteMusicianUseCaseForm {

    private String bandUuid;
    private String musicianUuid;
}
