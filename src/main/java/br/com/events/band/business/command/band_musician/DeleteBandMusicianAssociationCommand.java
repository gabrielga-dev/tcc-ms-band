package br.com.events.band.business.command.band_musician;

import br.com.events.band.adapter.repository.BandMusicianRepository;
import br.com.events.band.data.model.table.band.BandMusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteBandMusicianAssociationCommand {

    private final BandMusicianRepository bandMusicianRepository;

    @Transactional
    public void execute(BandMusicianTable bandMusicianTable) {
        bandMusicianRepository.disassociateMusicianFromBand(
                bandMusicianTable.getBand().getUuid(),
                bandMusicianTable.getMusician().getUuid()
        );
    }
}
