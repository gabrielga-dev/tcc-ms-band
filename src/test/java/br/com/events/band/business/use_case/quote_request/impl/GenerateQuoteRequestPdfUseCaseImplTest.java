package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.document.quote_request.GenerateQuoteRequestPdfCommand;
import br.com.events.band.data.io.pdf.PdfDTOMock;
import br.com.events.band.data.io.pdf.PdfType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link GenerateQuoteRequestPdfUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class GenerateQuoteRequestPdfUseCaseImplTest {

    @InjectMocks
    private GenerateQuoteRequestPdfUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when no command is not found, then throw NoSuchElementException")
    void executeWhenNoCommandIsNotFoundThenThrowNoSuchElementException() {
        ReflectionTestUtils.setField(useCase, "commands", new ArrayList<GenerateQuoteRequestPdfCommand>());
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> useCase.execute(MockConstants.STRING, PdfType.LINEUP)
        );
    }

    @Test
    @DisplayName("execute - when no command is found, then return pdf information")
    void executeWhenNoCommandIsFoundThenReturnPdfInformation() {
        var mockedCommand = mock(GenerateQuoteRequestPdfCommand.class);
        var expectedResponse = PdfDTOMock.build();
        when(mockedCommand.isAccepted(any(PdfType.class))).thenReturn(true);
        when(mockedCommand.execute(anyString())).thenReturn(expectedResponse);

        ReflectionTestUtils.setField(
                useCase,
                "commands",
                new ArrayList<>(List.of(mockedCommand))
        );
        var returned = useCase.execute(MockConstants.STRING, PdfType.LINEUP);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(expectedResponse, returned);

        verify(mockedCommand, times(1)).execute(anyString());
    }
}
