package br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/**
 * This class holds every needed filter to filter all authenticated person's bands at use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class FindAuthenticatedPersonBandsUseCaseFilters {

    private String name;
    private LocalDateTime creationDateStart;
    private LocalDateTime creationDateEnd;
    private Pageable pageable;
}
