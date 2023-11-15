package br.com.events.band.newer.adapter.feign;

import br.com.events.band.newer.data.io.band.ServiceType;
import br.com.events.band.older.domain.io.feign.msAuth.person.getAuthenticatedPerson.out.GetAuthenticatedPersonInformationResult;

public interface MsAuthFeign {

    GetAuthenticatedPersonInformationResult getAuthenticatedPersonInformation(String jwtToken);

    void addServiceToPerson(String serviceUuid, ServiceType serviceType);
}
