package br.com.events.band.infrastructure.feign.msAuth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.events.band.application.config.feign.MyEventFeignClientConfiguration;
import br.com.events.band.domain.io.feign.msAuth.person.addServiceToPerson.AddServiceToPersonServiceType;
import br.com.events.band.domain.io.feign.msAuth.person.getAuthenticatedPerson.out.GetAuthenticatedPersonInformationResult;

/**
 * This interface communicates with MS-AUTH
 *
 * @author Gabriel Guimarães de Almeida
 */
@FeignClient(
    name = "person-ms-auth",
    url = "${feign.client.ms.auth.url}",
    configuration = MyEventFeignClientConfiguration.class
)
public interface PersonMsAuthFeignClient {

    @GetMapping("/v1/person")
    ResponseEntity<GetAuthenticatedPersonInformationResult> getAuthenticatedPersonInformation(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
    );

    @PostMapping("/v1/person/add-service/{serviceUuid}/{serviceType}")
    ResponseEntity<Void> addServiceToPerson(
        @PathVariable("serviceUuid") String serviceUuid,
        @PathVariable("serviceType") AddServiceToPersonServiceType serviceType
    );
}
