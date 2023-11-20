package br.com.events.band.business.command.band;

import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.adapter.repository.BandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveBandCommand {

    private final BandRepository bandRepository;

    public BandTable execute(BandTable bandTable){
        return bandRepository.save(bandTable);
    }
}
