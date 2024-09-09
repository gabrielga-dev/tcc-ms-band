package br.com.events.band.business.command.quote_request;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.messaging.sqs.sender.SqsMessageSender;
import br.com.events.band.data.io.quote_request.request.QuoteRequestCreationErrorMessage;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequestMock;
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
 * Tests for {@link SendQuoteRequestCreationErrorMessageCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendQuoteRequestCreationErrorMessageCommandTest {

    @InjectMocks
    private SendQuoteRequestCreationErrorMessageCommand command;

    @Mock
    private SqsMessageSender sqsMessageSender;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "quoteRequestCreationErrorMessageQueue", MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when called return saved result")
    void executeWhenCalledReturnSavedResult() {
        doNothing().when(sqsMessageSender).send(anyString(), any(QuoteRequestCreationErrorMessage.class));

        var request = QuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(() -> command.execute(request));

        verify(sqsMessageSender, times(1)).send(anyString(), any(QuoteRequestCreationErrorMessage.class));
    }
}
