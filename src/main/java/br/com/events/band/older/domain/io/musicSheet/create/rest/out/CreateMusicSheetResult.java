package br.com.events.band.older.domain.io.musicSheet.create.rest.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMusicSheetResult {

    private String fileUuid;
}
