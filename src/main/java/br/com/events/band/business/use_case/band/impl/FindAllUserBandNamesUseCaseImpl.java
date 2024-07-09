package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.band.FindAllUserBandNamesUseCase;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllUserBandNamesUseCaseImpl implements FindAllUserBandNamesUseCase {

    private final AuthService authService;
    private final FindBandCommand findBandCommand;

    @Override
    public Map<String, String> execute() {
        return findBandCommand.byPersonUuid(authService.getAuthenticatedPersonUuid())
                .parallelStream()
                .collect(
                        Collectors.toMap(
                                BandTable::getUuid,
                                BandTable::getName
                        )
                );
    }
}
