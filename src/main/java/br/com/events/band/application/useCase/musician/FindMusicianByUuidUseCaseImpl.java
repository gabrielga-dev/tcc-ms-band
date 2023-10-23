package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.io._new.musician.response.MusicianResponse;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.useCase.address.BuildAddressResponseUseCase;
import br.com.events.band.infrastructure.useCase.musician.FindMusicianByUuidUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMusicianByUuidUseCaseImpl implements FindMusicianByUuidUseCase {

    private final MusicianRepository musicianRepository;
    private final BuildAddressResponseUseCase buildAddressResponseUseCase;

    @Override
    public MusicianResponse execute(String bandUuid, String musicianUuid) {
        var musician = musicianRepository.findByUuidAndBand_UuidAndActiveTrue(musicianUuid, bandUuid)
                .orElseThrow(MusicianDoesNotExistException::new);

        var response = new MusicianResponse(musician);
        response.setAddress(buildAddressResponseUseCase.execute(musician.getAddress()));

        return response;
    }
}
