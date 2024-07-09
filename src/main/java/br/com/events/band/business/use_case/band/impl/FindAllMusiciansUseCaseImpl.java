package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.band.FindAllMusiciansUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.data.io.musician.response.MusicianResponse;
import br.com.events.band.data.model.table.musician.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllMusiciansUseCaseImpl implements FindAllMusiciansUseCase {

    private final AuthService authService;
    private final FindBandCommand findBandCommand;

    @Override
    public List<MusicianResponse> execute(String bandUuid) {
        var band = findBandCommand.byUuid(bandUuid).orElseThrow(BandNonExistenceException::new);
        if (!Objects.equals(authService.getAuthenticatedPersonUuid(), band.getOwnerUuid())) {
            throw new BandOwnerException();
        }
        return band.getAllMusicians()
                .stream()
                .filter(MusicianTable::getActive)
                .sorted(Comparator.comparing(MusicianTable::getFirstName))
                .map(MusicianResponse::new)
                .collect(Collectors.toList());
    }
}
