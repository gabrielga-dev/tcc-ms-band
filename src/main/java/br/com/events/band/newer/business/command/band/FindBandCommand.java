package br.com.events.band.newer.business.command.band;

import br.com.events.band.newer.adapter.reporitory.BandRepository;
import br.com.events.band.newer.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.newer.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.newer.data.table.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandCommand {

    private final BandRepository bandRepository;

    public Page<BandTable> byPerson(
            String personUuid,
            AuthenticatedPersonBandsCriteria criteria,
            Pageable pageable
    ) {
        return bandRepository.findByAuthenticatedPerson(
                pageable,
                personUuid,
                criteria.getName(),
                criteria.getCityId(),
                criteria.getStateIso(),
                criteria.getCountryIso(),
                criteria.getCreationDateStart(),
                criteria.getCreationDateEnd()
        );
    }

    public Page<BandTable> byCriteria(FindBandsCriteria criteria, Pageable pageable) {
        return bandRepository.findByCriteria(
                pageable,
                criteria.getName(),
                criteria.getCityId(),
                criteria.getStateIso(),
                criteria.getCountryIso()
        );
    }
}
