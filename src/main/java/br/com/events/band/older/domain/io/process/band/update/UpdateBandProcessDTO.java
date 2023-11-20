package br.com.events.band.older.domain.io.process.band.update;

import br.com.events.band.newer.data.model.table.BandTable;
import br.com.events.band.older.domain.io.band.update.useCase.in.UpdateBandUseCaseForm;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBandProcessDTO {

    private UpdateBandUseCaseForm useCaseForm;
    private BandTable entity;
}
