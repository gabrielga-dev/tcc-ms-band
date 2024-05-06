package br.com.events.band.adapter.feign;

import br.com.events.band.data.io.band.ServiceType;
import br.com.events.band.data.io.person.response.PersonResponse;
import br.com.events.band.data.io.person.response.PersonWithRoleResponse;

public interface MsAuthFeign {

    PersonWithRoleResponse getAuthenticatedPersonInformation(String jwtToken);

    PersonResponse findPersonInformationByCpf(String cpf);
    PersonResponse findPersonInformationByUuid(String uuid);

    void addServiceToPerson(String serviceUuid, ServiceType serviceType);
}
