package br.com.events.band.adapter.feign.client;

import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.adapter.feign.client.config.MyEventFeignClientConfiguration;
import br.com.events.band.data.io.band.ServiceType;
import br.com.events.band.data.io.person.response.PersonResponse;
import br.com.events.band.data.io.person.response.PersonWithRoleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "person-ms-auth",
        url = "${feign.client.ms.auth.url}",
        configuration = MyEventFeignClientConfiguration.class
)
public interface MsAuthFeignClient extends MsAuthFeign {

    @GetMapping("/v1/person")
    PersonWithRoleResponse getAuthenticatedPersonInformation(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
    );

    @GetMapping("/v1/person/cpf/{cpf}")
    PersonResponse findPersonInformationByCpf(@PathVariable String cpf);

    @GetMapping("/v1/person/uuid/{uuid}")
    PersonResponse findPersonInformationByUuid(@PathVariable String uuid);

    @PostMapping("/v1/person/add-service/{serviceUuid}/{serviceType}")
    void addServiceToPerson(
            @PathVariable("serviceUuid") String serviceUuid,
            @PathVariable("serviceType") ServiceType serviceType
    );
}
