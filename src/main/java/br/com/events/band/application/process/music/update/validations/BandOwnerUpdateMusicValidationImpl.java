package br.com.events.band.application.process.music.update.validations;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.application.process.exception.MusicNonExistenceException;
import br.com.events.band.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.domain.repository.MusicRepository;
import br.com.events.band.infrastructure.process.music.update.UpdateMusicValidation;
import br.com.events.band.util.AuthUtil;
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
