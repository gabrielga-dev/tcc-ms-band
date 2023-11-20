package br.com.events.band.newer.adapter.feign;

import br.com.events.band.newer.data.io.band.ServiceType;
import br.com.events.band.newer.data.io.person.response.PersonResponse;

public interface MsAuthFeign {

    PersonResponse getAuthenticatedPersonInformation(String jwtToken);

    PersonResponse findPersonInformationByCpf(String cpf);

    void addServiceToPerson(String serviceUuid, ServiceType serviceType);
}
