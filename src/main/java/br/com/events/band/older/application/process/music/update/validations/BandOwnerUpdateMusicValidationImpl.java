package br.com.events.band.older.application.process.music.update.validations;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.exception.music.MusicNonExistenceException;
import br.com.events.band.older.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.domain.repository.MusicRepository;
import br.com.events.band.older.infrastructure.process.music.update.UpdateMusicValidation;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerUpdateMusicValidationImpl implements UpdateMusicValidation {

    private final MusicRepository musicRepository;
    private final BandRepository bandRepository;

    @Override
    public void validate(UpdateMusicUseCaseForm toValidate) {
        var musicOpt = musicRepository.findById(toValidate.getMusicUuid());

        if (musicOpt.isEmpty() || !musicOpt.get().getActive()) {
            throw new MusicNonExistenceException();
        }

        var music = musicOpt.get();

        var band = bandRepository.findById(music.getBand().getUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())) {
            throw new BandOwnerException();
        }
    }
}
