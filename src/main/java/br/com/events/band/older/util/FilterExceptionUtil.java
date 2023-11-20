package br.com.events.band.older.util;

import br.com.events.band.newer.core.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
