package br.com.events.band.business.command.quote.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.adapter.messaging.sqs.sender.SqsMessageSender;
import br.com.events.band.data.io.email.request.RawEmailRequest;
import br.com.events.band.data.io.event.response.EventProfileResponseMock;
import br.com.events.band.data.io.person.response.PersonResponseMock;
import br.com.events.band.data.model.table.quote.QuoteTableMock;
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
 * Tests for {@link SendQuoteAnswerEmailMessageCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendQuoteAnswerEmailMessageCommandImplTest {

    @InjectMocks
    private SendQuoteAnswerEmailMessageCommandImpl command;

    @Mock
    private MsAuthFeign msAuthFeign;
    @Mock
    private MsEventFeign msEventFeign;
    @Mock
    private SqsMessageSender sqsMessageSender;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "emailMessageQueue", MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when is hired then send message")
    void executeWhenIsHiredThenSendMessage() {
        var eventProfile = EventProfileResponseMock.build();
        when(msEventFeign.findProfile(anyString())).thenReturn(eventProfile);

        var personResponse = PersonResponseMock.build();
        when(msAuthFeign.findPersonInformationByUuid(anyString())).thenReturn(personResponse);

        doNothing().when(sqsMessageSender).send(anyString(), any(RawEmailRequest.class));

        var quote = QuoteTableMock.build();
        Assertions.assertDoesNotThrow(
                () -> command.execute(quote, true)
        );

        verify(msEventFeign, times(1)).findProfile(anyString());
        verify(msAuthFeign, times(1)).findPersonInformationByUuid(anyString());
        verify(sqsMessageSender, times(1)).send(anyString(), any(RawEmailRequest.class));
    }

    @Test
    @DisplayName("execute - when is not hired then send message")
    void executeWhenIsNotHiredThenSendMessage() {
        var eventProfile = EventProfileResponseMock.build();
        when(msEventFeign.findProfile(anyString())).thenReturn(eventProfile);

        var personResponse = PersonResponseMock.build();
        when(msAuthFeign.findPersonInformationByUuid(anyString())).thenReturn(personResponse);

        doNothing().when(sqsMessageSender).send(anyString(), any(RawEmailRequest.class));

        var quote = QuoteTableMock.build();
        Assertions.assertDoesNotThrow(
                () -> command.execute(quote, false)
        );

        verify(msEventFeign, times(1)).findProfile(anyString());
        verify(msAuthFeign, times(1)).findPersonInformationByUuid(anyString());
        verify(sqsMessageSender, times(1)).send(anyString(), any(RawEmailRequest.class));
    }
}
