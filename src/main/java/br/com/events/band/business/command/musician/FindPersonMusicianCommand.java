package br.com.events.band.business.command.musician;

import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.core.exception.BusinessException;
import br.com.events.band.data.io.person.response.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindPersonMusicianCommand {

    private final MsAuthFeign msAuthFeign;

    public Optional<PersonResponse> byCpf(String musicianCpf) {
        try {
            return Optional.of(msAuthFeign.findPersonInformationByCpf(musicianCpf));
        } catch (BusinessException businessException) {
            if (!businessException.isRequestFault()) {
                throw businessException;
            }
            return Optional.empty();
        }
    }
}
