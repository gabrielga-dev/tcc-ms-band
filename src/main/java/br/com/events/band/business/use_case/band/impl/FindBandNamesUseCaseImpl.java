package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.use_case.band.FindBandNamesUseCase;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindBandNamesUseCaseImpl implements FindBandNamesUseCase {

    private final FindBandCommand findBandCommand;

    @Override
    public Map<String, String> execute(List<String> bandUuids) {
        return findBandCommand.findAllByUuid(bandUuids)
                .stream()
                .collect(
                        Collectors.toMap(
                                BandTable::getUuid,
                                BandTable::getName
                        )
                );
    }
}
