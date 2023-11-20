package br.com.events.band.older.infrastructure.feign.msAuth;

import br.com.events.band.newer.adapter.feign.client.config.MyEventFeignClientConfiguration;
import br.com.events.band.newer.data.io.band.ServiceType;
import br.com.events.band.newer.data.io.person.response.PersonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

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
    ResponseEntity<PersonResponse> getAuthenticatedPersonInformation(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
    );

    @PostMapping("/v1/person/add-service/{serviceUuid}/{serviceType}")
    ResponseEntity<Void> addServiceToPerson(
        @PathVariable("serviceUuid") String serviceUuid,
        @PathVariable("serviceType") ServiceType serviceType
    );
}
