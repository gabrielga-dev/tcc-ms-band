package br.com.events.band.infrastructure.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class holds every needed information about any exception that is thrown inside this microservice
 *
 * @author Gabriel Guimarães de Almeida
 */
@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatusCode;

    private final Integer code;

    private final String message;

    private final String description;

    public BusinessExceptionBody getOnlyBody() {
        return BusinessExceptionBody.builder()
            .code(this.code)
            .message(this.message)
            .description(this.description)
            .build();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BusinessExceptionBody {

        private Integer code;

        private String message;

        private String description;

    }
}
