package br.com.events.band.domain.io.location.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information to cache every city
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class CachedCountryStateCity {

    private Long id;
    private String name;
}
