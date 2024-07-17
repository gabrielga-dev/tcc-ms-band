package br.com.events.band.business.command.quote.impl;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.messaging.sqs.sender.SqsMessageSender;
import br.com.events.band.data.io.quote.message.QuoteCreatedMessage;
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

/**
 * Tests for {@link SendQuoteCreationMessageCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendQuoteCreationMessageCommandImplTest {

    @InjectMocks
    private SendQuoteCreationMessageCommandImpl command;

    @Mock
    private SqsMessageSender sqsMessageSender;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "queue", MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when called send message")
    void executeWhenCalledSendMessage() {
        doNothing().when(sqsMessageSender).send(anyString(), any(QuoteCreatedMessage.class));

        var quote = QuoteTableMock.build();
        Assertions.assertDoesNotThrow(() -> command.execute(quote));

        verify(sqsMessageSender, times(1)).send(anyString(), any(QuoteCreatedMessage.class));
    }
}
