package br.com.events.band.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.events.band.infrastructure.exception.BusinessException;
import lombok.RequiredArgsConstructor;

/**
 * This class returns the error that appears at filters
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class FilterExceptionUtil {

    private final ObjectMapper objectMapper;

    public void setResponseError(HttpServletResponse servletResponse, BusinessException be) throws IOException {
        servletResponse.setContentType("application/json");
        servletResponse.setStatus(be.getOnlyBody().getCode());
        servletResponse.getWriter().write(objectMapper.writeValueAsString(be.getOnlyBody()));
    }
}
