package br.com.events.band.business.command.document.quote_request.templates;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.business.command.document.FindDocumentTemplateCommand;
import br.com.events.band.business.command.document.GeneratePdfDocumentCommand;
import br.com.events.band.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.band.data.io.document.PdfConfigurationDTO;
import br.com.events.band.data.io.event.response.EventProfileResponseMock;
import br.com.events.band.data.io.pdf.PdfType;
import br.com.events.band.data.model.table.band.BandTableMock;
import br.com.events.band.data.model.table.document.DocumentTemplateTableMock;
import br.com.events.band.data.model.table.quote.QuoteTableMock;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link GeneratePlaylistQuoteRequestPdfCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class GeneratePlaylistQuoteRequestPdfCommandTest {

    @InjectMocks
    private GeneratePlaylistQuoteRequestPdfCommand command;

    @Mock
    private MsEventFeign msEventFeign;
    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @Mock
    private FindDocumentTemplateCommand findDocumentTemplateCommand;
    @Mock
    protected GeneratePdfDocumentCommand generatePdfDocumentCommand;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "findQuoteRequestCommand", findQuoteRequestCommand);
        ReflectionTestUtils.setField(command, "findDocumentTemplateCommand", findDocumentTemplateCommand);
        ReflectionTestUtils.setField(command, "generatePdfDocumentCommand", generatePdfDocumentCommand);
    }

    @Test
    @DisplayName("getAcceptedPdfType - when called return playlist")
    void getAcceptedPdfTypeWhenCalledReturnPlaylist() {
        var returned = command.getAcceptedPdfType();
        Assertions.assertEquals(PdfType.PLAYLIST, returned);
    }

    @Test
    @DisplayName("execute - when called return pdf values")
    void executeWhenCalledReturnPdfValues() {
        when(findDocumentTemplateCommand.findByIdOrThrow(anyLong())).thenReturn(DocumentTemplateTableMock.build());

        var quoteRequest = QuoteRequestTableMock.build();
        quoteRequest.setQuote(QuoteTableMock.build());
        quoteRequest.setBand(BandTableMock.build());
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequest);

        var profile = EventProfileResponseMock.build();
        when(msEventFeign.findProfile(anyString())).thenReturn(profile);

        when(generatePdfDocumentCommand.execute(any(PdfConfigurationDTO.class), anyMap())).thenReturn(new byte[10]);

        var returned = command.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals("playlist.pdf", returned.getFileName());
        Assertions.assertNotNull(returned.getFileBytes());
        Assertions.assertEquals(10, returned.getFileBytes().length);

        verify(findDocumentTemplateCommand, times(1)).findByIdOrThrow(anyLong());
        verify(msEventFeign, times(1)).findProfile(MockConstants.STRING);
        verify(generatePdfDocumentCommand, times(1)).execute(any(PdfConfigurationDTO.class), anyMap());
    }

}
