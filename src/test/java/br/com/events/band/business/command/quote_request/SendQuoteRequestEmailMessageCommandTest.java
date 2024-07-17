package br.com.events.band.business.command.quote_request;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.adapter.messaging.sqs.sender.SqsMessageSender;
import br.com.events.band.data.io.email.request.RawEmailRequest;
import br.com.events.band.data.io.event.response.EventProfileResponseMock;
import br.com.events.band.data.io.person.response.PersonResponseMock;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequestMock;
import br.com.events.band.data.model.table.band.BandTableMock;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SendQuoteRequestEmailMessageCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendQuoteRequestEmailMessageCommandTest {

    @InjectMocks
    private SendQuoteRequestEmailMessageCommand command;

    @Mock
    private MsAuthFeign msAuthFeign;
    @Mock
    private SqsMessageSender sqsMessageSender;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "emailMessageQueue", MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when called return saved result")
    void executeWhenCalledReturnSavedResult() {
        var person = PersonResponseMock.build();
        when(msAuthFeign.findPersonInformationByUuid(anyString())).thenReturn(person);

        doNothing().when(sqsMessageSender).send(anyString(), any(RawEmailRequest.class));

        var request = QuoteRequestRequestMock.build();
        var event = EventProfileResponseMock.build();
        var band = BandTableMock.build();
        Assertions.assertDoesNotThrow(() -> command.execute(request, event, band));

        verify(sqsMessageSender, times(1)).send(anyString(), any(RawEmailRequest.class));
    }
}
