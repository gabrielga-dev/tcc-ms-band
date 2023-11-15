package br.com.events.band.older.domain.io.musicSheet.update.useCase.in;


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
public class UpdateSheetMusicUseCaseForm {

    private String sheetUuid;
    private String observation;
}
