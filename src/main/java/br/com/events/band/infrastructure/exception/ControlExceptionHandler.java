package br.com.events.band.infrastructure.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class makes the exception data extraction to return a clean error response
 *
 * @author Gabriel Guimarães de Almeida
 */
@ControllerAdvice
public class ControlExceptionHandler {

    /**
     * This method handle any {@link BusinessException}
     *
     * @param ex {@link BusinessException} object with the needed data to extract
     * @param request {@link WebRequest} with the request's data
     * @return {@link ResponseEntity}<{@link Object}> response entity with the mapped exception
     */
    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleException(BusinessException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
    }

    /**
     * This method handle any {@link MethodArgumentNotValidException}. These exceptions are thrown when a incoming data
     * does not matches to the validation.
     *
     * @param ex {@link MethodArgumentNotValidException} object with the needed data to extract
     * @param request {@link WebRequest} with the request's data
     * @return {@link ResponseEntity}<{@link Object}> response entity with the mapped exception
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        var errorMessages = ex.getAllErrors().stream()
            .map(
                error -> String.format(Objects.requireNonNull(error.getDefaultMessage()))
            ).collect(
                Collectors.joining("\n")
            );
        var toReturn = BusinessException.BusinessExceptionBody.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message("Campo(s) inválido(s)!")
            .description(errorMessages.split("\n")[0])
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).body(toReturn);
    }

}
