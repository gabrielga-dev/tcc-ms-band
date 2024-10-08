package br.com.events.band.business.command.band;

import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.data.io.band.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignBandToPersonCommand {

    private final MsAuthFeign msAuthFeign;

    public void execute(String bandUuid) {
        msAuthFeign.addServiceToPerson(bandUuid, ServiceType.BAND);
    }
}
