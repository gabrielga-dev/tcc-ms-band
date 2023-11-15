package br.com.events.band.newer.adapter.feign.client.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * This class does the basic feign client configuration for the communication among EVENTS project's MSs
 *
 * @author Gabriel Guimarães de Almeida
 */
public class MyEventFeignClientConfiguration extends BaseFeignClientConfig {

    @Value("${api.key.header}")
    private String apiKeyHeaderName;

    @Value("${api.key.header.value}")
    private String apiKeyHeaderValue;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            var token = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getHeader(HttpHeaders.AUTHORIZATION);

            requestTemplate.header(apiKeyHeaderName, apiKeyHeaderValue);
            requestTemplate.header(HttpHeaders.AUTHORIZATION, token);
        };
    }
}
