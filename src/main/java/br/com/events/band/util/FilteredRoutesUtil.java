package br.com.events.band.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class checks if the incoming path is not protected by JWT token filter
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
public class FilteredRoutesUtil {

    @Value("${filter.paths.excluded}")
    private List<String> excludePaths;

    public Boolean isRouteNotProtected(String route) {
        return excludePaths.stream().anyMatch(
            route::matches
        );
    }
}
