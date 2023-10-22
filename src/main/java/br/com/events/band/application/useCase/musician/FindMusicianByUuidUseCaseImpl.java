package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.io.musician.list.useCase.out.ListMusiciansUseCaseResult;
import br.com.events.band.domain.mapper.musician.ListMusicianMapper;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.useCase.musician.FindMusicianByUuidUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMusicianByUuidUseCaseImpl implements FindMusicianByUuidUseCase {

    private final MusicianRepository musicianRepository;

    @Override
    public ListMusiciansUseCaseResult execute(String bandUuid, String musicianUuid) {
        var musician = musicianRepository.findByUuidAndBand_UuidAndActiveTrue(musicianUuid, bandUuid)
                .orElseThrow(MusicianDoesNotExistException::new);

        return ListMusicianMapper.from(musician);
    }
}
