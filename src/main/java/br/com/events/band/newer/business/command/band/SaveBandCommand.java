package br.com.events.band.newer.business.command.band;

import br.com.events.band.newer.adapter.reporitory.BandRepository;
import br.com.events.band.newer.data.table.BandTable;
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
