package br.com.events.band.business.command.band_musician;

import br.com.events.band.adapter.repository.BandMusicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteAllBandMusicianAssociationsCommand {

    private final BandMusicianRepository bandMusicianRepository;

    @Transactional
    public void execute(String musicianUuid) {
        bandMusicianRepository.disassociateMusicianFromBands(musicianUuid);
    }
}
