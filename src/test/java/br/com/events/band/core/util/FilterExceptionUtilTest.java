package br.com.events.band.core.util;

import br.com.events.band.MockConstants;
import br.com.events.band.core.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FilterExceptionUtil}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
public class FilterExceptionUtilTest {

    @InjectMocks
    private FilterExceptionUtil util;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("setResponseError - when called calls correct methods")
    void setResponseErrorWhenCalledCallsCorrectMethods() throws IOException {
        var mockedWriter = mock(PrintWriter.class);
        doNothing().when(mockedWriter).write(anyString());

        var mockedServlet = mock(HttpServletResponse.class);
        doNothing().when(mockedServlet).setContentType(anyString());
        doNothing().when(mockedServlet).setStatus(anyInt());
        when(mockedServlet.getWriter()).thenReturn(mockedWriter);

        when(objectMapper.writeValueAsString(any(BusinessException.BusinessExceptionBody.class)))
                .thenReturn(MockConstants.STRING);

        var be = mock(BusinessException.class);
        when(be.getOnlyBody()).thenReturn(mock(BusinessException.BusinessExceptionBody.class));

        Assertions.assertDoesNotThrow(
                () -> util.setResponseError(mockedServlet, be)
        );
    }
}
