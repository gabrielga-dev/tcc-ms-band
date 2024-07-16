package br.com.events.band.business.command.band;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.data.io.band.ServiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link AssignBandToPersonCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class AssignBandToPersonCommandTest {

    @InjectMocks
    private AssignBandToPersonCommand command;

    @Mock
    private MsAuthFeign msAuthFeign;

    @Test
    @DisplayName("execute - when called calls feign client")
    void executeWhenCalledCallsFeignClient() {
        doNothing().when(msAuthFeign).addServiceToPerson(anyString(), eq(ServiceType.BAND));

        Assertions.assertDoesNotThrow(() -> command.execute(MockConstants.STRING));

        verify(msAuthFeign, times(1)).addServiceToPerson(anyString(), any(ServiceType.class));
    }
}
