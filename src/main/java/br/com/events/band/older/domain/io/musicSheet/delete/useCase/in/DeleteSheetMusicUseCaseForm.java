package br.com.events.band.older.domain.io.musicSheet.delete.useCase.in;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteSheetMusicUseCaseForm {

    private String sheetUuid;
}
