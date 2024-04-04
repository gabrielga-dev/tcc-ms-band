package br.com.events.band.business.use_case.band;

import java.util.List;
import java.util.Map;

public interface FindBandNamesUseCase {

    Map<String, String> execute(List<String> bandUuids);
}
