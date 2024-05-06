package br.com.events.band.business.command.band;

import br.com.events.band.adapter.repository.BandRepository;
import br.com.events.band.adapter.repository.jpa.BandJpaRepository;
import br.com.events.band.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindBandCommand {

    private final BandRepository bandRepository;

    public Optional<BandTable> byUuidAndOwnerUuid(String bandUuid, String ownerUuid) {
        return bandRepository.findByUuidAndOwnerUuid(bandUuid, ownerUuid);
    }

    public Optional<BandTable> byUuid(String bandUuid) {
        return bandRepository.findById(bandUuid);
    }

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
                true,
                criteria.getName(),
                criteria.getCityId(),
                criteria.getStateIso(),
                criteria.getCountryIso());
    }

    public List<BandTable> findAllByUuid(List<String> uuids) {
        return bandRepository.findAllByUuid(uuids);
    }
}
