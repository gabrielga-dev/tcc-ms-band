package br.com.events.band.newer.data.io.band.criteria;

import br.com.events.band.older.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class holds every needed filter to filter all authenticated person's bands at rest controller layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
public class AuthenticatedPersonBandsCriteria extends FindBandsCriteria {

    private Long creationDateStartMilliseconds;
    private Long creationDateEndMilliseconds;

    public LocalDateTime getCreationDateStart() {
        return DateUtil.millisecondsToLocalDateTime(creationDateStartMilliseconds);
    }

    public LocalDateTime getCreationDateEnd() {
        return DateUtil.millisecondsToLocalDateTime(creationDateEndMilliseconds);
    }
}
