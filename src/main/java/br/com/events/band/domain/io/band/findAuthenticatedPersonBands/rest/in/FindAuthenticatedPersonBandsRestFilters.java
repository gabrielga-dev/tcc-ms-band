package br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed filter to filter all authenticated person's bands at rest controller layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class FindAuthenticatedPersonBandsRestFilters {

    private String name;
    private Long creationDateStartMilliseconds;
    private Long creationDateEndMilliseconds;
}
