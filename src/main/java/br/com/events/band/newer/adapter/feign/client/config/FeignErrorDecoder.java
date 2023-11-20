package br.com.events.band.newer.adapter.feign.client.config;

import br.com.events.band.newer.core.exception.BusinessException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class decodes every error that came from feign clients
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, final Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            log.error("Error at {} {}", response.request().httpMethod(), response.request().url());

            var objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            var bodyError = objectMapper.readValue(
                bodyIs.readAllBytes(), BusinessException.BusinessExceptionBody.class
            );

            return new BusinessException(
                HttpStatus.valueOf(bodyError.getCode()),
                bodyError.getCode(),
                bodyError.getMessage(),
                bodyError.getDescription()
            );
        } catch (IOException e) {
            log.error(
                "Error mapping feign error at {} {}. {}",
                response.request().httpMethod(),
                response.request().url(),
                e.getMessage()
            );

            return new BusinessException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro ao tentar ler mensagem de falha dos servidores.",
                "A chamada a um dos nossos servidores falhou e o motivo ainda não foi mapeado."
            );
        }
    }
}
